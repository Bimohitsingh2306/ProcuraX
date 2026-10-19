package com.company.procuraX.dto;

import com.company.procuraX.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class InventoryMovementResponseDTO {

    private String movementId;
    private String productId;
    private String warehouseId;
    private MovementType movementType;
    private int quantity;
    private String referenceId;
    private LocalDateTime movementDate;
}