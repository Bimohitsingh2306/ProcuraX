package com.company.procuraX.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SupplierResponseDTO {
    private String supplierId;
    private String supplierName;
    private String email;
    private String phone;
    private int averageDeliveryDays;
    private double reliabilityScore;
}
