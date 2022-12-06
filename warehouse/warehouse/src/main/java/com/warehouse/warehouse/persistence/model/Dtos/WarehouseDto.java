package com.warehouse.warehouse.persistence.model.Dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class WarehouseDto {

    private String location;

    private Double totalCapacityTon;

    private Double currentAmountTon;

    private String region;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PurchaseProductDto> purchaseProductDtoList;

    public WarehouseDto() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTotalCapacityTon() {
        return totalCapacityTon;
    }

    public void setTotalCapacityTon(Double totalCapacityTon) {
        this.totalCapacityTon = totalCapacityTon;
    }

    public Double getCurrentAmountTon() {
        return currentAmountTon;
    }

    public void setCurrentAmountTon(Double currentAmountTon) {
        this.currentAmountTon = currentAmountTon;
    }

    public List<PurchaseProductDto> getPurchaseProductDtoList() {
        return purchaseProductDtoList;
    }

    public void setPurchaseProductDtoList(List<PurchaseProductDto> purchaseProductDtoList) {
        this.purchaseProductDtoList = purchaseProductDtoList;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
