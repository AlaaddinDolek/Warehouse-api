package com.warehouse.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.WarehouseNotFoundException;
import com.warehouse.warehouse.management.WarehouseManager;
import com.warehouse.warehouse.persistence.model.Dtos.WarehouseDto;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseManager warehouseManager;

    public WarehouseDto getWarehouseByLocation(String location) throws WarehouseNotFoundException {
        return warehouseManager.getWarehouseByLocation(location).toWarehouseDto();
    }

    public WarehouseDto getStorehoseByWarehouseId(Long warehouseId) throws WarehouseNotFoundException {
        return warehouseManager.getWarehouseByWarehouseId(warehouseId).toWarehouseDto();
    }

    public List<WarehouseDto> getAllWarehouses() {
        return warehouseManager.warehouseListToWarehouseDtoList(warehouseManager.getAllWarehouses());
    }

    public void deleteWarehouseById(Long warehouseId) throws WarehouseNotFoundException {
        warehouseManager.deleteWarehouseById(warehouseId);
    }

    public WarehouseDto createWarehouse(String location, Double totalCapacityTon, String region)
            throws WarehouseNotFoundException {
        return warehouseManager.createWarehouse(location, totalCapacityTon, region).toWarehouseDto();
    }

    public List<WarehouseDto> getWarehouseByCapacityGreaterThanEqual(Double ton) throws WarehouseNotFoundException {
        return warehouseManager
                .warehouseListToWarehouseDtoList(warehouseManager.getWarehouseByCapacityGreaterThanEqual(ton));
    }

    public List<WarehouseDto> getWarehouseByCapacityLessThanEqual(Double ton) throws WarehouseNotFoundException {
        return warehouseManager
                .warehouseListToWarehouseDtoList(warehouseManager.getWarehouseByCapacityLessThanEqual(ton));
    }

    public List<WarehouseDto> getWarehouseByProductName(String productName)
            throws ProductNotFoundException, WarehouseNotFoundException {
        return warehouseManager
                .warehouseListToWarehouseDtoList(warehouseManager.getWarehouseByProductName(productName));
    }

    public List<WarehouseDto> getWarehouseByProductType(String productType)
            throws ProductNotFoundException, WarehouseNotFoundException {
        return warehouseManager
                .warehouseListToWarehouseDtoList(warehouseManager.getWarehouseByProductType(productType));
    }

    public List<WarehouseDto> getWarehouseByRegion(String region) throws WarehouseNotFoundException {
        return warehouseManager.warehouseListToWarehouseDtoList(warehouseManager.getWarehouseByRegion(region));
    }

}
