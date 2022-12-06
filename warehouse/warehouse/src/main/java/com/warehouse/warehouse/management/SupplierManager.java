package com.warehouse.warehouse.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.SupplierAlreadyExistsException;
import com.warehouse.warehouse.exceptions.SupplierNotFoundException;
import com.warehouse.warehouse.persistence.model.Supplier;
import com.warehouse.warehouse.persistence.model.Dtos.SupplierDto;
import com.warehouse.warehouse.persistence.repository.SupplierRepository;

@Service
public class SupplierManager {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier toSupplier(SupplierDto supplierDto) throws SupplierNotFoundException {
        Optional<Supplier> supplier = supplierRepository.findSupplierBySupplierName(supplierDto.getSupplierName());
        if (!supplier.isPresent())
            throw new SupplierNotFoundException("Supplier not found");

        return supplier.get();
    }

    public Supplier getSupplierByName(String supplierName) throws SupplierNotFoundException {

        Optional<Supplier> supplier = supplierRepository.findSupplierBySupplierName(supplierName);
        if (!supplier.isPresent())
            throw new SupplierNotFoundException("Supplier not found");

        return supplier.get();
    }

    public Long createSupplier(String supplierName, String supplierLocation) throws SupplierAlreadyExistsException {

        Optional<Supplier> optionalSupplier = supplierRepository.findSupplierBySupplierName(supplierName);
        if (optionalSupplier.isPresent())
            throw new SupplierAlreadyExistsException("Supplier already exists");

        Supplier supplier = new Supplier();

        supplier.setSupplierName(supplierName);
        supplier.setSupplierLocation(supplierLocation);

        return supplierRepository.save(supplier).getSupplierId();
    }

    public List<Supplier> getSuppliersByLocation(String location) {
        return supplierRepository.findSupplierBySupplierLocation(location);
    }

    public Supplier getSupplierById(Long supplierId) throws SupplierNotFoundException {
        Optional<Supplier> supplier = supplierRepository.findSupplierBySupplierId(supplierId);
        if (!supplier.isPresent())
            throw new SupplierNotFoundException("Supplier not found");

        return supplier.get();
    }

    public Supplier updateSupplier(Supplier supplier) throws SupplierAlreadyExistsException, SupplierNotFoundException {
        Supplier supplierById = getSupplierById(supplier.getSupplierId());

        Optional<Supplier> supplierByName = supplierRepository.findSupplierBySupplierName(supplier.getSupplierName());
        if (supplierByName.isPresent())
            throw new SupplierAlreadyExistsException("Supplier already exists");

        if (supplier.getSupplierLocation() != null)
            supplierById.setSupplierLocation(supplier.getSupplierLocation());
        if (supplier.getSupplierName() != null)
            supplierById.setSupplierName(supplier.getSupplierName());

        return supplierRepository.save(supplierById);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void deleteBySupplierId(Long supplierId) throws SupplierNotFoundException {
        supplierRepository.deleteById(supplierId);
    }

    public List<SupplierDto> supplierListToSupplierDtoList(List<Supplier> supplierList) {
        List<SupplierDto> supplierDtoList = new ArrayList<>();

        for (Supplier supplier : supplierList) {
            supplierDtoList.add(supplier.toSupplierDto());
        }
        return supplierDtoList;
    }

}
