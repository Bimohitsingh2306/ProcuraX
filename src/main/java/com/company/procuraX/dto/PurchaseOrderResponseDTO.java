package com.company.procuraX.dto;

import com.company.procuraX.model.PurchaseOrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PurchaseOrderResponseDTO {

    private String purchaseOrderId;
    private String supplierId;
    private String productId;
    private int quantity;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private PurchaseOrderStatus status;
    private double unitPrice;
}
