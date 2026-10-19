package com.company.procuraX.procurement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BestSupplierResponseDTO {

    private String supplierId;
    private String supplierName;
    private double reliabilityScore;
    private double qualityScore;
    private double averageDelayDays;
    private String recommendationReason;
}
