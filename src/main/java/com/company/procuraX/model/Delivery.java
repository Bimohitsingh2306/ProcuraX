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
@Table(name = "deliveries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @Column(name = "delivery_id")
    @NotBlank(message = "Delivery ID is required")
    private String deliveryId;

    @Column(name = "purchase_order_id")
    @NotBlank(message = "Purchase Order ID is required")
    private String purchaseOrderId;

    @Column(name = "delivery_date")
    @NotNull(message = "Delivery date is required")
    private LocalDate deliveryDate;

    @Column(name = "expected_delivery_date")
    @NotNull(message = "Expected delivery date is required")
    private LocalDate expectedDeliveryDate;

    @Column(name = "delivered_quantity")
    @Min(value = 0, message = "Delivered quantity cannot be negative")
    private int deliveredQuantity;

    @Column(name = "accepted_quantity")
    @Min(value = 0, message = "Accepted quantity cannot be negative")
    private int acceptedQuantity;

    @Column(name = "rejected_quantity")
    @Min(value = 0, message = "Rejected quantity cannot be negative")
    private int rejectedQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Delivery status is required")
    private DeliveryStatus status;
}
