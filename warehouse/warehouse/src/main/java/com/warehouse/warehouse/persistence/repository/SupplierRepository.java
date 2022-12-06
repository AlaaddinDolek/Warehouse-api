package com.warehouse.warehouse.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.warehouse.warehouse.persistence.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    List<Supplier> findAll();

    Optional<Supplier> findSupplierBySupplierId(Long supplierId);

    Optional<Supplier> findSupplierBySupplierName(String supplierName);

    List<Supplier> findSupplierBySupplierLocation(String location);

    void deleteBySupplierId(Long supplierId);

}