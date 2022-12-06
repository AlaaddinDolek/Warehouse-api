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

import com.warehouse.warehouse.exceptions.ProductAlreadyExistsException;
import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.SupplierNotFoundException;
import com.warehouse.warehouse.persistence.model.Product;
import com.warehouse.warehouse.persistence.model.Dtos.ProductDto;
import com.warehouse.warehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Long createProduct(@RequestBody ProductDto productDto)
            throws SupplierNotFoundException, ProductAlreadyExistsException {
        return productService.createProduct(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProductById(@RequestParam Long productId) {
        productService.deleteProductById(productId);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/byType")
    public List<ProductDto> getProductsByType(@RequestParam String productType) throws ProductNotFoundException {
        return productService.getProductsByType(productType);
    }

    @GetMapping("/byName")
    public ProductDto getProductByName(@RequestParam String productName) throws ProductNotFoundException {
        return productService.getProductByName(productName);
    }

    @GetMapping("/byId")
    public ProductDto getProductById(@RequestParam Long productId) throws ProductNotFoundException {
        return productService.getProductById(productId);
    }

    @GetMapping("/bySupplier")
    public List<ProductDto> getProductsBySupplier(@RequestParam String supplierName) throws SupplierNotFoundException {
        return productService.getProductsBySupplier(supplierName);
    }

    @GetMapping("/byGreaterThanEqual")
    public List<ProductDto> getProductByGreaterThanEqual(@RequestParam Double tonPrice) {
        return productService.getProductsByGreaterThanEqual(tonPrice);
    }

    @GetMapping("/byLessThan")
    public List<ProductDto> getProductByLessThan(@RequestParam Double tonPrice) {
        return productService.getProductsByLessThan(tonPrice);
    }

}
