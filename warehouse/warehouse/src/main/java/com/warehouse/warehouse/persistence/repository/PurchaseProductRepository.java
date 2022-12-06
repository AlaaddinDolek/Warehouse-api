package com.warehouse.warehouse.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.warehouse.warehouse.persistence.model.Client;
import com.warehouse.warehouse.persistence.model.PurchaseProduct;

public interface PurchaseProductRepository extends CrudRepository<PurchaseProduct, Long> {

    List<PurchaseProduct> findAll();

    Optional<PurchaseProduct> findByPurchaseProductId(Long purchaseProductId);

    Optional<PurchaseProduct> findByPurchaseProductIdAndStatus(Long purchaseProductId, String status);

    Optional<PurchaseProduct> findByClientAndStatus(Client client, String status);

    void deleteByPurchaseProductId(Long purchaseProductId);

    List<PurchaseProduct> findAllByStatusChangeDateAfter(LocalDateTime statusDate);
}
