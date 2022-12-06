package com.warehouse.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.SupplierAlreadyExistsException;
import com.warehouse.warehouse.exceptions.SupplierNotFoundException;
import com.warehouse.warehouse.management.SupplierManager;
import com.warehouse.warehouse.persistence.model.Supplier;
import com.warehouse.warehouse.persistence.model.Dtos.SupplierDto;

@Service
public class SupplierService {

    @Autowired
    private SupplierManager supplierManager;

    public Long createSupplier(String supplierName, String supplierLocation) throws SupplierAlreadyExistsException {
        return supplierManager.createSupplier(supplierName, supplierLocation);
    }

    public SupplierDto getSupplierByName(String supplierName) throws SupplierNotFoundException {
        return supplierManager.getSupplierByName(supplierName).toSupplierDto();
    }

    public List<SupplierDto> getSuppliersByLocation(String location) {
        return supplierManager.supplierListToSupplierDtoList(supplierManager.getSuppliersByLocation(location));
    }

    public SupplierDto updateSupplier(Supplier supplier)
            throws SupplierAlreadyExistsException, SupplierNotFoundException {
        return supplierManager.updateSupplier(supplier).toSupplierDto();
    }

    public List<SupplierDto> getAllSuppliers() {
        return supplierManager.supplierListToSupplierDtoList(supplierManager.getAllSuppliers());
    }

    public void deleteBySupplierId(Long supplierId) throws SupplierNotFoundException {
        supplierManager.deleteBySupplierId(supplierId);
    }

}
