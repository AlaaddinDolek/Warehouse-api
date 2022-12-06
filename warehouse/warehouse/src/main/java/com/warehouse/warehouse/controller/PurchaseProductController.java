package com.warehouse.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.warehouse.exceptions.ClientNotFoundException;
import com.warehouse.warehouse.exceptions.OverCapacityException;
import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.WarehouseNotFoundException;
import com.warehouse.warehouse.persistence.model.Dtos.PurchaseProductDto;
import com.warehouse.warehouse.service.PurchaseProductService;

@RestController
@RequestMapping("/purchaseProduct")
public class PurchaseProductController {

    @Autowired
    private PurchaseProductService purchaseProductService;

    @PostMapping
    public PurchaseProductDto purchaseProduct(@RequestParam Long productId, Double ton, Long warehouseId)
            throws ProductNotFoundException, OverCapacityException, WarehouseNotFoundException {
        return purchaseProductService.purchaseProduct(productId, ton, warehouseId);
    }

    @PutMapping("/receive")
    public PurchaseProductDto received(@RequestParam Long purchaseProductId) throws ProductNotFoundException {
        return purchaseProductService.received(purchaseProductId);
    }

    @GetMapping("/byId")
    public PurchaseProductDto getPurchaseProductById(@RequestParam Long purchaseProductId)
            throws ProductNotFoundException {
        return purchaseProductService.getPurchaseProductById(purchaseProductId);
    }

    @GetMapping("/reserve")
    public PurchaseProductDto reserveProduct(@RequestParam Long clientId, Long purchaseProductId, Double ton)
            throws ClientNotFoundException, ProductNotFoundException {
        return purchaseProductService.reserveProduct(clientId, purchaseProductId, ton);
    }
}
