package com.warehouse.warehouse.exceptions;

public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
