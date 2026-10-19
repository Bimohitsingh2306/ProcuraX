package com.company.procuraX.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierPerformanceRequestDTO {

    @NotBlank(message = "Performance ID is required")
    private String performanceId;

    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @Min(value = 0, message = "Total orders cannot be negative")
    private int totalOrders;

    @Min(value = 0, message = "Completed orders cannot be negative")
    private int completedOrders;

    @Min(value = 0, message = "Late orders cannot be negative")
    private int lateOrders;

    @Min(value = 0, message = "Average delay days cannot be negative")
    private double averageDelayDays;

    @Min(value = 0, message = "Quality score cannot be less than 0")
    @Max(value = 100, message = "Quality score cannot be greater than 100")
    private double qualityScore;

    @Min(value = 0, message = "Reliability score cannot be less than 0")
    @Max(value = 100, message = "Reliability score cannot be greater than 100")
    private double reliabilityScore;
}
