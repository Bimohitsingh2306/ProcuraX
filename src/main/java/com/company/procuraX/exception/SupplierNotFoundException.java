package com.company.procuraX.exception;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message){
        super(message);
    }
}
