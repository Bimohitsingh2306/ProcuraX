package com.company.procuraX.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Current stock cannot be negative")
    private int currentStock;

    @Min(value = 0, message = "Minimum stock cannot be negative")
    private int minimumStock;

    @Min(value = 0, message = "Daily demand cannot be negative")
    private int dailyDemand;
}
