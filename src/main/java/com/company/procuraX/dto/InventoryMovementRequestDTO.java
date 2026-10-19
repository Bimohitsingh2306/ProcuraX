package com.company.procuraX.dto;

import com.company.procuraX.model.MovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryMovementRequestDTO {

    @NotBlank(message = "Movement ID is required")
    private String movementId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotBlank(message = "Warehouse ID is required")
    private String warehouseId;

    private MovementType movementType;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    private String referenceId;
}
