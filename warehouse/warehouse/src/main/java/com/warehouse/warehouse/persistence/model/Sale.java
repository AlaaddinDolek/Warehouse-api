package com.warehouse.warehouse.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.warehouse.warehouse.persistence.model.Dtos.SaleDto;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @OneToOne(fetch = FetchType.EAGER)
    @Where(clause = "status='Sold'")
    private PurchaseProduct purchaseProduct;

    public Sale() {
    }

    public SaleDto toSaleDto() {
        SaleDto saleDto = new SaleDto();
        saleDto.setPurchaseProductDto(purchaseProduct.toPurchaseProductDto());
        Double price = purchaseProduct.getProduct().getTonPrice() * purchaseProduct.getSumTon();
        saleDto.setPrice(price);

        return saleDto;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public PurchaseProduct getPurchaseProduct() {
        return purchaseProduct;
    }

    public void setPurchaseProduct(PurchaseProduct purchaseProduct) {
        this.purchaseProduct = purchaseProduct;
    }

}
