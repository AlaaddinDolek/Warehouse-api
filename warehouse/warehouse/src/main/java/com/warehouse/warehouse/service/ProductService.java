package com.warehouse.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ProductAlreadyExistsException;
import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.SupplierNotFoundException;
import com.warehouse.warehouse.management.ProductManager;
import com.warehouse.warehouse.persistence.model.Product;
import com.warehouse.warehouse.persistence.model.Dtos.ProductDto;

@Service
public class ProductService {

    @Autowired
    private ProductManager productManager;

    public Long createProduct(ProductDto productDto) throws SupplierNotFoundException, ProductAlreadyExistsException {
        return productManager.createProduct(productDto);
    }

    public ProductDto updateProduct(Product product) throws ProductNotFoundException {
        return productManager.updateProduct(product).toProductDto();
    }

    public void deleteProductById(Long productId) {
        productManager.deleteProductById(productId);
    }

    public List<ProductDto> getAllProducts() {
        return productManager.productListToProductDtoList(productManager.getAllProducts());
    }

    public List<ProductDto> getProductsByType(String productType) throws ProductNotFoundException {
        return productManager.productListToProductDtoList(productManager.getProductsByType(productType));
    }

    public ProductDto getProductByName(String productName) throws ProductNotFoundException {
        return productManager.getProductByName(productName).toProductDto();
    }

    public ProductDto getProductById(Long productId) throws ProductNotFoundException {
        return productManager.getProductById(productId).toProductDto();
    }

    public List<ProductDto> getProductsBySupplier(String supplierName) throws SupplierNotFoundException {
        return productManager.productListToProductDtoList(productManager.getProductsBySupplier(supplierName));
    }

    public List<ProductDto> getProductsByGreaterThanEqual(Double tonPrice) {
        return productManager.productListToProductDtoList(productManager.getProductsByGreaterThanEqual(tonPrice));
    }

    public List<ProductDto> getProductsByLessThan(Double tonPrice) {
        return productManager.productListToProductDtoList(productManager.getProductsByLessThan(tonPrice));
    }

}
