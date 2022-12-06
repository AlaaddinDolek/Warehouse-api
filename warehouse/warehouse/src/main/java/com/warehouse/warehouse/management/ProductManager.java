package com.warehouse.warehouse.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ProductAlreadyExistsException;
import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.SupplierNotFoundException;
import com.warehouse.warehouse.persistence.model.Product;
import com.warehouse.warehouse.persistence.model.Supplier;
import com.warehouse.warehouse.persistence.model.Dtos.ProductDto;
import com.warehouse.warehouse.persistence.repository.ProductRepository;

@Service
public class ProductManager {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierManager supplierManager;

    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findProductByProductId(id);
        if (!product.isPresent())
            throw new ProductNotFoundException("Product not found");

        return product.get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByType(String productType) throws ProductNotFoundException {
        List<Product> productList = productRepository.findProductsByProductType(productType);
        if (productList.isEmpty())
            throw new ProductNotFoundException("Product type not found");

        return productList;
    }

    public Product getProductByName(String productName) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findProductByProductName(productName);
        if (!product.isPresent())
            throw new ProductNotFoundException("Product not found");

        return product.get();
    }

    public List<Product> getProductsBySupplier(String supplierName) throws SupplierNotFoundException {
        Supplier supplier = supplierManager.getSupplierByName(supplierName);
        List<Product> productList = productRepository.findProductsBySupplier(supplier);

        return productList;
    }

    public List<Product> getProductsByGreaterThanEqual(Double tonPrice) {
        List<Product> productList = productRepository.findByTonPriceGreaterThanEqual(tonPrice);

        return productList;
    }

    public List<Product> getProductsByLessThan(Double tonPrice) {
        List<Product> productList = productRepository.findByTonPriceLessThan(tonPrice);

        return productList;
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {

        Product updatedProduct = getProductById(product.getProductId());

        if (product.getProductName() != null)
            updatedProduct.setProductName(product.getProductName());
        if (product.getPurchaseProduct() != null)
            updatedProduct.setPurchaseProduct(product.getPurchaseProduct());
        if (product.getSupplier() != null)
            updatedProduct.setSupplier(product.getSupplier());
        if (product.getTonPrice() != null)
            updatedProduct.setTonPrice(product.getTonPrice());
        if (product.getProductType() != null)
            updatedProduct.setProductType(product.getProductType());

        return productRepository.save(updatedProduct);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Long createProduct(ProductDto productDto) throws SupplierNotFoundException, ProductAlreadyExistsException {
        Product product = new Product();

        Supplier supplier = supplierManager.getSupplierByName(productDto.getSupplierName());

        Optional<Product> optionalProduct = productRepository.findProductByProductName(productDto.getProductName());
        if (optionalProduct.isPresent())
            throw new ProductAlreadyExistsException("Product already exists");

        product.setProductName(productDto.getProductName());
        product.setSupplier(supplier);
        product.setTonPrice(productDto.getTonPrice());
        product.setSupplier(supplier);
        product.setProductType(productDto.getProductType());

        return productRepository.save(product).getProductId();
    }

    public List<ProductDto> productListToProductDtoList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            productDtoList.add(product.toProductDto());
        }
        return productDtoList;
    }

    public Product saveToRepository(Product product) {
        return productRepository.save(product);
    }

}
