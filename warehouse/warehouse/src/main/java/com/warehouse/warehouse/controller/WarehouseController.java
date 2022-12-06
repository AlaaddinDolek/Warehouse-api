package com.warehouse.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.WarehouseNotFoundException;
import com.warehouse.warehouse.persistence.model.Dtos.WarehouseDto;
import com.warehouse.warehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public WarehouseDto createWarehouse(@RequestParam String location, Double totalCapacityTon, String region)
            throws WarehouseNotFoundException {
        return warehouseService.createWarehouse(location, totalCapacityTon, region);
    }

    @DeleteMapping
    public void deleteWarehouseById(@RequestParam Long warehouseId) throws WarehouseNotFoundException {
        warehouseService.deleteWarehouseById(warehouseId);
    }

    @GetMapping
    public List<WarehouseDto> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/byLocation")
    public WarehouseDto getWarehouseByLocation(@RequestParam String location) throws WarehouseNotFoundException {
        return warehouseService.getWarehouseByLocation(location);
    }

    @GetMapping("/byWarehouseId")
    public WarehouseDto getWarehouseByWarehouseId(@RequestParam Long warehouseId) throws WarehouseNotFoundException {
        return warehouseService.getStorehoseByWarehouseId(warehouseId);
    }

    @GetMapping("/capacityGreaterThan")
    public List<WarehouseDto> getWarehouseByCapacityGreaterThanEqual(@RequestParam Double ton)
            throws WarehouseNotFoundException {
        return warehouseService.getWarehouseByCapacityGreaterThanEqual(ton);
    }

    @GetMapping("/capacityLessThan")
    public List<WarehouseDto> getWarehouseByCapacityLessThanEqual(@RequestParam Double ton)
            throws WarehouseNotFoundException {
        return warehouseService.getWarehouseByCapacityLessThanEqual(ton);
    }

    @GetMapping("byProductName")
    public List<WarehouseDto> getWarehouseByProductName(@RequestParam String productName)
            throws ProductNotFoundException, WarehouseNotFoundException {
        return warehouseService.getWarehouseByProductName(productName);
    }

    @GetMapping("/byProductType")
    public List<WarehouseDto> getWarehouseByProductType(@RequestParam String productType)
            throws ProductNotFoundException, WarehouseNotFoundException {
        return warehouseService.getWarehouseByProductType(productType);
    }

    @GetMapping("/byRegion")
    public List<WarehouseDto> getWarehouseByRegion(@RequestParam String region) throws WarehouseNotFoundException {
        return warehouseService.getWarehouseByRegion(region);
    }

}