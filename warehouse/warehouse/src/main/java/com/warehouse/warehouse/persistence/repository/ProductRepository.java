package com.warehouse.warehouse.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.warehouse.warehouse.persistence.model.Product;
import com.warehouse.warehouse.persistence.model.Supplier;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findProductByProductId(Long produtcId);

    Optional<Product> findProductByProductName(String productName);

    List<Product> findProductsByProductType(String productType);

    List<Product> findProductsBySupplier(Supplier supplierName);

    List<Product> findByTonPriceGreaterThanEqual(Double tonPrice);

    List<Product> findByTonPriceLessThan(Double tonPrice);
}
