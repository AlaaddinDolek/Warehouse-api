package com.warehouse.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.warehouse.exceptions.ReserveTimeOutException;
import com.warehouse.warehouse.exceptions.SaleNotFoundException;
import com.warehouse.warehouse.persistence.model.Dtos.SaleDto;
import com.warehouse.warehouse.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/getAllSales")
    public List<SaleDto> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/bySaleId")
    public SaleDto getSaleById(@RequestParam Long saleId) throws SaleNotFoundException {
        return saleService.getSaleById(saleId);
    }

    @GetMapping
    public Long saleOrder(@RequestParam Long reserveId)
            throws ProductNotFoundException, ReserveTimeOutException {
        return saleService.saleOrder(reserveId);
    }
}
