package com.company.procuraX.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @Column(name = "inventory_id")
    @NotBlank(message = "Inventory ID is required")
    private String inventoryId;

    @Column(name = "product_id")
    @NotBlank(message = "Product ID is required")
    private String productId;

    @Column(name = "warehouse_id")
    @NotBlank(message = "Warehouse ID is required")
    private String warehouseId;

    @Column(name = "quantity")
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @Column(name = "reserved_quantity")
    @Min(value = 0, message = "Reserved quantity cannot be negative")
    private int reservedQuantity;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}