package com.warehouse.warehouse.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.warehouse.warehouse.persistence.model.Dtos.ProductDto;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productType;

    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseProduct purchaseProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    private Double tonPrice;

    public Product() {
    }

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();

        productDto.setProductName(productName);
        productDto.setSupplierName(supplier.getSupplierName());
        productDto.setTonPrice(tonPrice);
        productDto.setProductType(productType);

        return productDto;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public PurchaseProduct getPurchaseProduct() {
        return purchaseProduct;
    }

    public void setPurchaseProduct(PurchaseProduct purchaseProduct) {
        this.purchaseProduct = purchaseProduct;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Double getTonPrice() {
        return tonPrice;
    }

    public void setTonPrice(Double tonPrice) {
        this.tonPrice = tonPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((productType == null) ? 0 : productType.hashCode());
        result = prime * result + ((purchaseProduct == null) ? 0 : purchaseProduct.hashCode());
        result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
        result = prime * result + ((tonPrice == null) ? 0 : tonPrice.hashCode());
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
        Product other = (Product) obj;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        if (productType == null) {
            if (other.productType != null)
                return false;
        } else if (!productType.equals(other.productType))
            return false;
        if (purchaseProduct == null) {
            if (other.purchaseProduct != null)
                return false;
        } else if (!purchaseProduct.equals(other.purchaseProduct))
            return false;
        if (supplier == null) {
            if (other.supplier != null)
                return false;
        } else if (!supplier.equals(other.supplier))
            return false;
        if (tonPrice == null) {
            if (other.tonPrice != null)
                return false;
        } else if (!tonPrice.equals(other.tonPrice))
            return false;
        return true;
    }

}
