package com.warehouse.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ClientNotFoundException;
import com.warehouse.warehouse.exceptions.OverCapacityException;
import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.WarehouseNotFoundException;
import com.warehouse.warehouse.management.PurchaseProductManager;
import com.warehouse.warehouse.persistence.model.Dtos.PurchaseProductDto;

@Service
public class PurchaseProductService {

    @Autowired
    private PurchaseProductManager purchaseProductManager;

    public PurchaseProductDto purchaseProduct(Long productId, Double ton, Long warehouseId)
            throws ProductNotFoundException, WarehouseNotFoundException, OverCapacityException {
        return purchaseProductManager.purchaseProduct(productId, ton, warehouseId).toPurchaseProductDto();
    }

    public PurchaseProductDto received(Long purchaseProductId) throws ProductNotFoundException {
        return purchaseProductManager.received(purchaseProductId).toPurchaseProductDto();
    }

    public PurchaseProductDto getPurchaseProductById(Long purchaseProductId) throws ProductNotFoundException {
        return purchaseProductManager.getPurchaseProductById(purchaseProductId).toPurchaseProductDto();
    }

    public PurchaseProductDto reserveProduct(Long clientId, Long purchaseProductId, Double ton)
            throws ClientNotFoundException, ProductNotFoundException {
        return purchaseProductManager.reserveProduct(clientId, purchaseProductId, ton).toPurchaseProductDto();
    }

}
