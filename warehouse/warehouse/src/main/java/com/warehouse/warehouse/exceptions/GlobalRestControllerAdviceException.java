package com.warehouse.warehouse.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviceException {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails clientNotFoundException(ClientNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Client not found !", ex.getMessage());
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails locationNotFoundException(LocationNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Location not found !", ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails productNotFoundException(ProductNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Product not found !", ex.getMessage());
    }

    @ExceptionHandler(SaleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails saleNotFoundException(SaleNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Sale not found !", ex.getMessage());
    }

    @ExceptionHandler(WarehouseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails warehouseHouseNotFoundException(WarehouseNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Warehouse not found !", ex.getMessage());
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails supplierNotFoundException(SupplierNotFoundException ex) {
        return new CustomErrorDetails(new Date(), "Supplier not found !", ex.getMessage());
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails clientAlreadyExistsException(ClientAlreadyExistsException ex) {
        return new CustomErrorDetails(new Date(), "Client already exists !", ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails productAlreadyExistsException(ProductAlreadyExistsException ex) {
        return new CustomErrorDetails(new Date(), "Product already exists !", ex.getMessage());
    }

    @ExceptionHandler(OverCapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails overCapacityException(OverCapacityException ex) {
        return new CustomErrorDetails(new Date(), "Over capacity !", ex.getMessage());
    }

    @ExceptionHandler(ProductAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails productAmountException(ProductAmountException ex) {
        return new CustomErrorDetails(new Date(), "Over product amount !", ex.getMessage());
    }

    @ExceptionHandler(ReserveTimeOutException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails reserveTimeOutException(ReserveTimeOutException ex) {
        return new CustomErrorDetails(new Date(), "Time out !", ex.getMessage());
    }

}
