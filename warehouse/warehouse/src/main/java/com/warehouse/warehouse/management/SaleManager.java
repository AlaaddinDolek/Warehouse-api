package com.warehouse.warehouse.management;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.ReserveTimeOutException;
import com.warehouse.warehouse.exceptions.SaleNotFoundException;
import com.warehouse.warehouse.persistence.model.PurchaseProduct;
import com.warehouse.warehouse.persistence.model.Sale;
import com.warehouse.warehouse.persistence.model.Dtos.SaleDto;
import com.warehouse.warehouse.persistence.repository.PurchaseProductRepository;
import com.warehouse.warehouse.persistence.repository.SaleRepository;

@Service
public class SaleManager {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private PurchaseProductManager purchaseProductManager;

    @Autowired
    private PurchaseProductRepository purchaseProductRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public List<SaleDto> saleListToSaleDtoList(List<Sale> saleList) {
        List<SaleDto> saleDtoList = new ArrayList<>();

        for (Sale sale : saleList) {
            saleDtoList.add(sale.toSaleDto());
        }
        return saleDtoList;
    }

    public Sale getSaleById(Long saleId) throws SaleNotFoundException {
        Optional<Sale> sale = saleRepository.findBySaleId(saleId);
        if (!sale.isPresent()) {
            throw new SaleNotFoundException("Sale not found");
        }

        return sale.get();
    }

    public Long saleOrder(Long reserveId) throws ProductNotFoundException, ReserveTimeOutException {
        PurchaseProduct reservedProduct = purchaseProductManager.getPurchaseProductById(reserveId);

        if (reservedProduct.getStatus().equals("TimeOut")) {
            throw new ReserveTimeOutException("Timeout");
        }

        if (!reservedProduct.getStatus().equals("Reserved"))
            throw new ProductNotFoundException("Product Not Found");

        Sale sale = new Sale();

        reservedProduct.setStatus("Sold");
        reservedProduct.setStatusChangeDate(LocalDateTime.now());
        reservedProduct.setArrivalDate(LocalDate.now().plusDays(7));

        purchaseProductRepository.save(reservedProduct);
        sale.setPurchaseProduct(reservedProduct);
        saleRepository.save(sale);
        return sale.getSaleId();
    }
}
