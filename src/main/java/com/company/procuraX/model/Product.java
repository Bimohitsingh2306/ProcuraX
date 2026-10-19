package com.company.procuraX.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @NotBlank(message = "Product ID is required")
    private String productId;

    @Column(name = "product_name")
    @NotBlank(message = "Product name is required")
    private String productName;

    @Column(name = "category")
    @NotBlank(message = "Category is required")
    private String category;

    @Column(name = "current_stock")
    @Min(value = 0, message = "Current stock cannot be negative")
    private int currentStock;

    @Column(name = "minimum_stock")
    @Min(value = 0, message = "Minimum stock cannot be negative")
    private int minimumStock;

    @Column(name = "daily_demand")
    @Min(value = 0, message = "Daily demand cannot be negative")
    private int dailyDemand;
}
