package com.warehouse.warehouse.persistence.model.Dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SupplierDto {

    public String supplierName;
    public String supplierLocation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ProductDto> supplierProductList;

    public SupplierDto() {

    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierLocation() {
        return supplierLocation;
    }

    public void setSupplierLocation(String supplierLocation) {
        this.supplierLocation = supplierLocation;
    }

    public List<ProductDto> getSupplierProductList() {
        return supplierProductList;
    }

    public void setSupplierProductList(List<ProductDto> supplierProductList) {
        this.supplierProductList = supplierProductList;
    }

}
