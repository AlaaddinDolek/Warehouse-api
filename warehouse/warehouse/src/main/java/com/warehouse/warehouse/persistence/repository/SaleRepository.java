package com.warehouse.warehouse.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.warehouse.warehouse.persistence.model.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    List<Sale> findAll();

    Optional<Sale> findBySaleId(Long saleId);
}
