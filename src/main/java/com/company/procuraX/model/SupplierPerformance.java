package com.company.procuraX.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier_performance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPerformance {

    @Id
    @Column(name = "performance_id")
    @NotBlank(message = "Performance ID is required")
    private String performanceId;

    @Column(name = "supplier_id")
    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @Column(name = "total_orders")
    @Min(value = 0, message = "Total orders cannot be negative")
    private int totalOrders;

    @Column(name = "completed_orders")
    @Min(value = 0, message = "Completed orders cannot be negative")
    private int completedOrders;

    @Column(name = "late_orders")
    @Min(value = 0, message = "Late orders cannot be negative")
    private int lateOrders;

    @Column(name = "average_delay_days")
    @Min(value = 0, message = "Average delay days cannot be negative")
    private double averageDelayDays;

    @Column(name = "quality_score")
    @Min(value = 0, message = "Quality score cannot be less than 0")
    @Max(value = 100, message = "Quality score cannot be greater than 100")
    private double qualityScore;

    @Column(name = "reliability_score")
    @Min(value = 0, message = "Reliability score cannot be less than 0")
    @Max(value = 100, message = "Reliability score cannot be greater than 100")
    private double reliabilityScore;
}