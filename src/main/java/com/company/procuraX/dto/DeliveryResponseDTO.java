package com.company.procuraX.dto;

import com.company.procuraX.model.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DeliveryResponseDTO {

    private String deliveryId;
    private String purchaseOrderId;
    private LocalDate deliveryDate;
    private LocalDate expectedDeliveryDate;
    private int deliveredQuantity;
    private int acceptedQuantity;
    private int rejectedQuantity;
    private DeliveryStatus status;
}