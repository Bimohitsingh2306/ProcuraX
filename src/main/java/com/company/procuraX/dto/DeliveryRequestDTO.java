package com.company.procuraX.dto;

import com.company.procuraX.model.DeliveryStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryRequestDTO {

    @NotBlank(message = "Delivery ID is required")
    private String deliveryId;

    @NotBlank(message = "Purchase Order ID is required")
    private String purchaseOrderId;

    @NotNull(message = "Delivery date is required")
    private LocalDate deliveryDate;

    @NotNull(message = "Expected delivery date is required")
    private LocalDate expectedDeliveryDate;

    @Min(value = 0, message = "Delivered quantity cannot be negative")
    private int deliveredQuantity;

    @Min(value = 0, message = "Accepted quantity cannot be negative")
    private int acceptedQuantity;

    @Min(value = 0, message = "Rejected quantity cannot be negative")
    private int rejectedQuantity;

    @NotNull(message = "Delivery status is required")
    private DeliveryStatus status;
}
