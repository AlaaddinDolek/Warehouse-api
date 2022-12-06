package com.warehouse.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.ReserveTimeOutException;
import com.warehouse.warehouse.exceptions.SaleNotFoundException;
import com.warehouse.warehouse.management.SaleManager;
import com.warehouse.warehouse.persistence.model.Dtos.SaleDto;

@Service
public class SaleService {
    @Autowired
    private SaleManager saleManager;

    public List<SaleDto> getAllSales() {
        return saleManager.saleListToSaleDtoList(saleManager.getAllSales());
    }

    public SaleDto getSaleById(Long saleId) throws SaleNotFoundException {
        return saleManager.getSaleById(saleId).toSaleDto();
    }

    public Long saleOrder(Long reserveId) throws ProductNotFoundException, ReserveTimeOutException {
        return saleManager.saleOrder(reserveId);
    }
}
