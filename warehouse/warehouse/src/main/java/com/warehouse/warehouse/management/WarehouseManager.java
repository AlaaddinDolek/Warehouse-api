package com.warehouse.warehouse.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.WarehouseNotFoundException;
import com.warehouse.warehouse.persistence.model.PurchaseProduct;
import com.warehouse.warehouse.persistence.model.Warehouse;
import com.warehouse.warehouse.persistence.model.Dtos.WarehouseDto;
import com.warehouse.warehouse.persistence.repository.WarehouseRepository;

@Service
public class WarehouseManager {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductManager productManager;

    public List<WarehouseDto> warehouseListToWarehouseDtoList(List<Warehouse> warehouseList) {
        List<WarehouseDto> warehouseDtoList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            warehouseDtoList.add(warehouse.toWarehouseDto());
        }
        return warehouseDtoList;
    }

    public Warehouse getWarehouseByLocation(String location) throws WarehouseNotFoundException {
        Optional<Warehouse> warehouse = warehouseRepository.findWarehouseByLocation(location);
        if (!warehouse.isPresent())
            throw new WarehouseNotFoundException("Warehouse not found");
        return warehouse.get();
    }

    public Warehouse getWarehouseByWarehouseId(Long warehouseId) throws WarehouseNotFoundException {
        Optional<Warehouse> warehouse = warehouseRepository.findWarehouseByWarehouseId(warehouseId);
        if (!warehouse.isPresent())
            throw new WarehouseNotFoundException("Warehouse not found");
        return warehouse.get();
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public void deleteWarehouseById(Long warehouseId) throws WarehouseNotFoundException {
        getWarehouseByWarehouseId(warehouseId);
        warehouseRepository.deleteById(warehouseId);
    }

    public Warehouse createWarehouse(String location, Double totalCapacityTon, String region)
            throws WarehouseNotFoundException {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocation(location);
        warehouse.setTotalCapacityTon(totalCapacityTon);
        warehouse.setRegion(region);

        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getWarehouseByCapacityGreaterThanEqual(Double ton) throws WarehouseNotFoundException {
        List<Warehouse> warehouses = warehouseRepository.findByTotalCapacityTonGreaterThanEqual(ton);
        if (warehouses.isEmpty())
            throw new WarehouseNotFoundException("There is no warehouse which has more capacity than" + ton);
        return warehouses;
    }

    public List<Warehouse> getWarehouseByCapacityLessThanEqual(Double ton) throws WarehouseNotFoundException {
        List<Warehouse> warehouses = warehouseRepository.findByTotalCapacityTonLessThanEqual(ton);
        if (warehouses.isEmpty())
            throw new WarehouseNotFoundException("There is no warehouse which has more capacity than" + ton);
        return warehouses;
    }

    public List<Warehouse> getWarehouseByProductName(String productName)
            throws ProductNotFoundException, WarehouseNotFoundException {
        productManager.getProductByName(productName);

        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if (warehouseList.isEmpty())
            throw new WarehouseNotFoundException("There is no warehouse which has" + productName);

        List<Warehouse> newWarehouseList = new ArrayList<>();

        for (Warehouse warehouse : warehouseRepository.findAll()) {
            for (PurchaseProduct purchaseProduct : warehouse.getPurchaseProductList()) {
                if (purchaseProduct.getProduct() == productManager.getProductByName(productName))
                    newWarehouseList.add(warehouse);
            }
        }
        return warehouseList;
    }

    public List<Warehouse> getWarehouseByProductType(String productType)
            throws ProductNotFoundException, WarehouseNotFoundException {
        productManager.getProductsByType(productType);

        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if (warehouseList.isEmpty())
            throw new WarehouseNotFoundException("There is no warehouse which has" + productType);

        List<Warehouse> newWarehouseList = new ArrayList<>();

        String type = productType;

        for (Warehouse warehouse : warehouseList) {
            for (PurchaseProduct purchaseProduct : warehouse.getPurchaseProductList()) {
                if (purchaseProduct.getProduct().getProductType() == type)
                    newWarehouseList.add(warehouse);
            }
        }
        return warehouseList;
    }

    public List<Warehouse> getWarehouseByRegion(String region) throws WarehouseNotFoundException {
        List<Warehouse> warehouseList = warehouseRepository.findByRegion(region);
        if (warehouseList.isEmpty())
            throw new WarehouseNotFoundException(region);

        return warehouseList;
    }
}
