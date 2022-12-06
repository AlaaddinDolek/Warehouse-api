package com.warehouse.warehouse.persistence.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.warehouse.warehouse.persistence.model.Dtos.PurchaseProductDto;

@Entity
@Table(name = "purchaseProduct")
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseProductId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;

    private Double sumTon;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    private LocalDate arrivalDate;

    private String status;

    private LocalDateTime statusChangeDate;

    @OneToOne(fetch = FetchType.EAGER)
    private Client client;

    public PurchaseProductDto toPurchaseProductDto() {
        PurchaseProductDto purchaseProductDto = new PurchaseProductDto();

        purchaseProductDto.setArrivalDate(arrivalDate);
        purchaseProductDto.setProductDto(product.toProductDto());
        purchaseProductDto.setStatus(status);
        purchaseProductDto.setWarehouseLocation(warehouse.getLocation());
        purchaseProductDto.setSumTon(sumTon);
        if (client != null) {
            purchaseProductDto.setClientName(client.getClientName());
        }
        if (statusChangeDate != null) {
            purchaseProductDto.setStatusChangeDate(statusChangeDate.toLocalDate());
        }

        return purchaseProductDto;
    }

    public PurchaseProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Long getPurchaseProductId() {
        return purchaseProductId;
    }

    public void setPurchaseProductId(Long purchaseProductId) {
        this.purchaseProductId = purchaseProductId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(LocalDateTime statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((purchaseProductId == null) ? 0 : purchaseProductId.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((sumTon == null) ? 0 : sumTon.hashCode());
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
        result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((statusChangeDate == null) ? 0 : statusChangeDate.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PurchaseProduct other = (PurchaseProduct) obj;
        if (purchaseProductId == null) {
            if (other.purchaseProductId != null)
                return false;
        } else if (!purchaseProductId.equals(other.purchaseProductId))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (sumTon == null) {
            if (other.sumTon != null)
                return false;
        } else if (!sumTon.equals(other.sumTon))
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        if (arrivalDate == null) {
            if (other.arrivalDate != null)
                return false;
        } else if (!arrivalDate.equals(other.arrivalDate))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (statusChangeDate == null) {
            if (other.statusChangeDate != null)
                return false;
        } else if (!statusChangeDate.equals(other.statusChangeDate))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        return true;
    }

}
