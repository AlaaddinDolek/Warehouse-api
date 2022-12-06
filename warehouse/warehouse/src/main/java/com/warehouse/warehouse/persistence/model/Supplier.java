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

import com.warehouse.warehouse.persistence.model.Dtos.ProductDto;
import com.warehouse.warehouse.persistence.model.Dtos.SupplierDto;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String supplierName;

    private String supplierLocation;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> productList;

    public SupplierDto toSupplierDto() {
        SupplierDto supplierDto = new SupplierDto();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            productDtoList.add(product.toProductDto());
        }
        supplierDto.setSupplierLocation(supplierLocation);
        supplierDto.setSupplierName(supplierName);
        supplierDto.setSupplierProductList(productDtoList);

        return supplierDto;
    }

    public Supplier() {
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((supplierId == null) ? 0 : supplierId.hashCode());
        result = prime * result + ((supplierName == null) ? 0 : supplierName.hashCode());
        result = prime * result + ((supplierLocation == null) ? 0 : supplierLocation.hashCode());
        result = prime * result + ((productList == null) ? 0 : productList.hashCode());
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
        Supplier other = (Supplier) obj;
        if (supplierId == null) {
            if (other.supplierId != null)
                return false;
        } else if (!supplierId.equals(other.supplierId))
            return false;
        if (supplierName == null) {
            if (other.supplierName != null)
                return false;
        } else if (!supplierName.equals(other.supplierName))
            return false;
        if (supplierLocation == null) {
            if (other.supplierLocation != null)
                return false;
        } else if (!supplierLocation.equals(other.supplierLocation))
            return false;
        if (productList == null) {
            if (other.productList != null)
                return false;
        } else if (!productList.equals(other.productList))
            return false;
        return true;
    }

}
