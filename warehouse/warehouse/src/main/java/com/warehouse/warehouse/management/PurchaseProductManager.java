package com.warehouse.warehouse.management;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ClientNotFoundException;
import com.warehouse.warehouse.exceptions.OverCapacityException;
import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.WarehouseNotFoundException;
import com.warehouse.warehouse.persistence.model.Client;
import com.warehouse.warehouse.persistence.model.Product;
import com.warehouse.warehouse.persistence.model.PurchaseProduct;
import com.warehouse.warehouse.persistence.model.Warehouse;
import com.warehouse.warehouse.persistence.repository.PurchaseProductRepository;

@Service
public class PurchaseProductManager {

    @Autowired
    private PurchaseProductRepository purchaseProductRepository;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private WarehouseManager warehouseManager;

    @Autowired
    private ClientManager clientManager;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void shipmentTracker(PurchaseProduct purchaseProduct) {
        executor.submit(() -> {
            try {
                Thread.sleep(10000);

                purchaseProduct.setStatus("Received");
                purchaseProduct.setStatusChangeDate(LocalDateTime.now());
                purchaseProductRepository.save(purchaseProduct);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void reserveTracker(Long purchaseProductId, Long reservedPurchaseProductId, Double ton) {
        executor.submit(() -> {
            try {
                Thread.sleep(10000);

                PurchaseProduct reservedPurchaseProduct = purchaseProductRepository
                        .findByPurchaseProductId(reservedPurchaseProductId).get();
                PurchaseProduct purchaseProduct = purchaseProductRepository.findByPurchaseProductId(purchaseProductId)
                        .get();

                if (reservedPurchaseProduct.getStatus().equals("Reserved")) {

                    Double sumTon = purchaseProduct.getSumTon() + ton;
                    purchaseProduct.setSumTon(sumTon);

                    reservedPurchaseProduct.setStatus("TimeOut");
                    reservedPurchaseProduct.setStatusChangeDate(LocalDateTime.now());
                    purchaseProductRepository.save(reservedPurchaseProduct);
                    purchaseProductRepository.save(purchaseProduct);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public PurchaseProduct purchaseProduct(Long productId, Double ton, Long warehouseId)
            throws ProductNotFoundException, WarehouseNotFoundException, OverCapacityException {

        Product product = productManager.getProductById(productId);
        Warehouse warehouse = warehouseManager.getWarehouseByWarehouseId(warehouseId);
        Double totalAmount = warehouse.toWarehouseDto().getCurrentAmountTon() + ton;

        if (totalAmount > warehouse.getTotalCapacityTon()) {
            throw new OverCapacityException("Over capacity");
        }

        PurchaseProduct purchaseProduct = new PurchaseProduct();
        purchaseProduct.setProduct(product);
        product.setPurchaseProduct(purchaseProduct);
        purchaseProduct.setWarehouse(warehouse);
        purchaseProduct.setSumTon(ton);
        purchaseProduct.setStatus("Shipped");
        purchaseProduct.setArrivalDate(LocalDate.now().plusDays(7));
        purchaseProduct.setStatusChangeDate(LocalDateTime.now());

        PurchaseProduct savedPurchaseProduct = purchaseProductRepository.save(purchaseProduct);
        shipmentTracker(savedPurchaseProduct);

        return savedPurchaseProduct;
    }

    public List<PurchaseProduct> getAllPurchaseddProducts() {
        return purchaseProductRepository.findAll();
    }

    public PurchaseProduct getPurchaseProductById(Long purchaseProductId) throws ProductNotFoundException {
        Optional<PurchaseProduct> purchaseProduct = purchaseProductRepository
                .findByPurchaseProductId(purchaseProductId);
        if (!purchaseProduct.isPresent())
            throw new ProductNotFoundException("Product not found");
        return purchaseProduct.get();
    }

    public PurchaseProduct received(Long purchaseProductId) throws ProductNotFoundException {
        PurchaseProduct purchaseProduct = getPurchaseProductById(purchaseProductId);
        purchaseProduct.setStatus("Received");

        return purchaseProductRepository.save(purchaseProduct);
    }

    public PurchaseProduct reserveProduct(Long clientId, Long purchaseProductId, Double ton)
            throws ClientNotFoundException, ProductNotFoundException {
        Client client = clientManager.getByClientId(clientId);
        PurchaseProduct wantedPurchaseProduct = getPurchaseProductById(purchaseProductId);
        PurchaseProduct reservedPurchaseProduct = new PurchaseProduct();

        List<Warehouse> warehouseList = warehouseManager.getAllWarehouses();
        for (Warehouse warehouse : warehouseList) {
            for (PurchaseProduct purchaseProduct : warehouse.getPurchaseProductList()) {
                if (purchaseProduct.equals(wantedPurchaseProduct)) {
                    Double productTon = purchaseProduct.getSumTon();
                    if (productTon > ton) {
                        reservedPurchaseProduct.setProduct(purchaseProduct.getProduct());
                        reservedPurchaseProduct.setStatus("Reserved");
                        reservedPurchaseProduct.setSumTon(ton);
                        reservedPurchaseProduct.setWarehouse(warehouse);
                        reservedPurchaseProduct.setStatusChangeDate(LocalDateTime.now());
                        reservedPurchaseProduct.setClient(client);
                        purchaseProductRepository.save(reservedPurchaseProduct);

                        Double afterReserve = productTon - ton;
                        purchaseProduct.setSumTon(afterReserve);

                        purchaseProductRepository.save(purchaseProduct);

                        reserveTracker(purchaseProduct.getPurchaseProductId(),
                                reservedPurchaseProduct.getPurchaseProductId(), ton);
                    }
                }
                break;
            }
            break;
        }
        return reservedPurchaseProduct;
    }

}
