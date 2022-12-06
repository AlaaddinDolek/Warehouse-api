package com.warehouse.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.warehouse.exceptions.SupplierAlreadyExistsException;
import com.warehouse.warehouse.exceptions.SupplierNotFoundException;
import com.warehouse.warehouse.persistence.model.Supplier;
import com.warehouse.warehouse.persistence.model.Dtos.SupplierDto;
import com.warehouse.warehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public Long createSupplier(@RequestParam String supplierName, String supplierLocation)
            throws SupplierAlreadyExistsException {
        return supplierService.createSupplier(supplierName, supplierLocation);
    }

    @PutMapping
    public SupplierDto updateSupplier(@RequestBody Supplier supplier)
            throws SupplierAlreadyExistsException, SupplierNotFoundException {
        return supplierService.updateSupplier(supplier);
    }

    @GetMapping
    public List<SupplierDto> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @DeleteMapping
    public void deleteSupplierById(@RequestParam Long supplierId) throws SupplierNotFoundException {
        supplierService.deleteBySupplierId(supplierId);
    }

    @GetMapping("/byName")
    public SupplierDto getSupplierByName(@RequestParam String supplierName) throws SupplierNotFoundException {
        return supplierService.getSupplierByName(supplierName);
    }

    @GetMapping("/byLocation")
    public List<SupplierDto> getSuppliersByLocation(@RequestParam String location) {
        return supplierService.getSuppliersByLocation(location);
    }

}