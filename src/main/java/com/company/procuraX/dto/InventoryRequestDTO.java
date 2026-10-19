package com.company.procuraX.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDTO {

    @NotBlank(message = "Inventory ID is required")
    private String inventoryId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotBlank(message = "Warehouse ID is required")
    private String warehouseId;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @Min(value = 0, message = "Reserved quantity cannot be negative")
    private int reservedQuantity;
}
