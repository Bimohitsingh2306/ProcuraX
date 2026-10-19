package com.company.procuraX.dto;

import com.company.procuraX.model.PurchaseOrderStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PurchaseOrderRequestDTO {

    @NotBlank(message = "Purchase Order ID is required")
    private String purchaseOrderId;

    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Expected delivery date is required")
    private LocalDate expectedDeliveryDate;

    @NotNull(message = "Purchase Order status is required")
    private PurchaseOrderStatus status;

    @Min(value = 0, message = "Unit price cannot be negative")
    private double unitPrice;
}