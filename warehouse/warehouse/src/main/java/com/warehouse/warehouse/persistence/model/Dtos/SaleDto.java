package com.warehouse.warehouse.persistence.model.Dtos;

public class SaleDto {
    private PurchaseProductDto purchaseProductDto;

    private Double price;

    public SaleDto() {
    }

    public PurchaseProductDto getPurchaseProductDto() {
        return purchaseProductDto;
    }

    public void setPurchaseProductDto(PurchaseProductDto purchaseProductDto) {
        this.purchaseProductDto = purchaseProductDto;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
