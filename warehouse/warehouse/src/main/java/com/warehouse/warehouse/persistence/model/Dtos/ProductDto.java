package com.warehouse.warehouse.persistence.model.Dtos;

public class ProductDto {

    private String productName;

    private String supplierName;

    private Double tonPrice;

    private String productType;

    public ProductDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getTonPrice() {
        return tonPrice;
    }

    public void setTonPrice(Double tonPrice) {
        this.tonPrice = tonPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
