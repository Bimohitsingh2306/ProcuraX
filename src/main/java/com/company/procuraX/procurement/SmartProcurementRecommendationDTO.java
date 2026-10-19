package com.company.procuraX.procurement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SmartProcurementRecommendationDTO {

    private String productId;
    private String productName;

    private int currentStock;
    private int minimumStock;
    private int dailyDemand;

    private int recommendedQuantity;

    private String supplierId;
    private String supplierName;

    private double reliabilityScore;
    private double qualityScore;
    private double averageDelayDays;

    private String recommendationReason;
}
