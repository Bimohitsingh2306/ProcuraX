package com.company.procuraX.procurement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReorderRecommendationDTO {

    private String productId;
    private String productName;
    private int currentStock;
    private int minimumStock;
    private int dailyDemand;
    private int recommendedQuantity;
    private String reason;
}
