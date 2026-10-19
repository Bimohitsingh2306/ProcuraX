package com.company.procuraX.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovement {

    @Id
    @Column(name = "movement_id")
    @NotBlank(message = "Movement ID is required")
    private String movementId;

    @Column(name = "product_id")
    @NotBlank(message = "Product ID is required")
    private String productId;

    @Column(name = "warehouse_id")
    @NotBlank(message = "Warehouse ID is required")
    private String warehouseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private MovementType movementType;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "movement_date")
    private LocalDateTime movementDate;
}
