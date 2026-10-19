package com.company.procuraX.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SupplierPerformanceResponseDTO {

    private String performanceId;
    private String supplierId;
    private int totalOrders;
    private int completedOrders;
    private int lateOrders;
    private double averageDelayDays;
    private double qualityScore;
    private double reliabilityScore;
}
