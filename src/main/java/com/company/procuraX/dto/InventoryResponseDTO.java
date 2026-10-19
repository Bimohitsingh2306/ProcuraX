package com.company.procuraX.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class InventoryResponseDTO {

    private String inventoryId;
    private String productId;
    private String warehouseId;
    private int quantity;
    private int reservedQuantity;
    private LocalDateTime lastUpdated;
}
