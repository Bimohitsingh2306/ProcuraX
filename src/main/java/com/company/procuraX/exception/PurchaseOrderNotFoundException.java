package com.company.procuraX.exception;

public class PurchaseOrderNotFoundException extends RuntimeException {

    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }
}