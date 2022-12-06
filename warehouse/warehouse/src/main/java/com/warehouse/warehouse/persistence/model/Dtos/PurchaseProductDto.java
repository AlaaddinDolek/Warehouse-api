package com.warehouse.warehouse.persistence.model.Dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PurchaseProductDto {

    private ProductDto productDto;

    private Double sumTon;

    private String warehouseLocation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate arrivalDate;

    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate statusChangeDate;

    public PurchaseProductDto() {

    }

    public Double getSumTon() {
        return sumTon;
    }

    public void setSumTon(Double sumTon) {
        this.sumTon = sumTon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(LocalDate statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

}
