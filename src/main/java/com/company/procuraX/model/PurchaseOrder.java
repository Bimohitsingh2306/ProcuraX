package com.company.procuraX.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    @Column(name = "purchase_order_id")
    @NotBlank(message = "Purchase Order ID is required")
    private String purchaseOrderId;

    @Column(name = "supplier_id")
    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @Column(name = "product_id")
    @NotBlank(message = "Product ID is required")
    private String productId;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Column(name = "order_date")
    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @Column(name = "expected_delivery_date")
    @NotNull(message = "Expected delivery date is required")
    private LocalDate expectedDeliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Purchase Order status is required")
    private PurchaseOrderStatus status;

    @Column(name = "unit_price")
    @Min(value = 0, message = "Unit price cannot be negative")
    private double unitPrice;
}