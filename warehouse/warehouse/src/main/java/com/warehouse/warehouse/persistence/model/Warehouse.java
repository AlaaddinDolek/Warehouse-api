package com.warehouse.warehouse.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.warehouse.warehouse.persistence.model.Dtos.PurchaseProductDto;
import com.warehouse.warehouse.persistence.model.Dtos.WarehouseDto;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String location;

    private Double totalCapacityTon;

    private String region;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "warehouse")
    private List<PurchaseProduct> purchaseProductList;

    public WarehouseDto toWarehouseDto() {
        WarehouseDto warehouseDto = new WarehouseDto();
        List<PurchaseProductDto> purchaseProductDtoList = new ArrayList<>();

        Double currentAmount = 0.0;
        if (purchaseProductList != null) {
            for (PurchaseProduct purchaseProduct : purchaseProductList) {
                purchaseProductDtoList.add(purchaseProduct.toPurchaseProductDto());
                if (purchaseProduct.getStatus() == "Received")
                    currentAmount += purchaseProduct.getSumTon();
            }
            warehouseDto.setPurchaseProductDtoList(purchaseProductDtoList);
        }

        warehouseDto.setCurrentAmountTon(currentAmount);
        warehouseDto.setLocation(location);
        warehouseDto.setTotalCapacityTon(totalCapacityTon);
        warehouseDto.setRegion(region);

        return warehouseDto;
    }

    public Warehouse() {
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

    public List<PurchaseProduct> getPurchaseProductList() {
        return purchaseProductList;
    }

    public void setPurchaseProductList(List<PurchaseProduct> purchaseProductList) {
        this.purchaseProductList = purchaseProductList;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
