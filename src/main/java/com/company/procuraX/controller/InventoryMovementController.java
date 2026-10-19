package com.company.procuraX.controller;

import com.company.procuraX.dto.InventoryMovementRequestDTO;
import com.company.procuraX.dto.InventoryMovementResponseDTO;
import com.company.procuraX.service.InventoryMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

    public InventoryMovementController(
            InventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<InventoryMovementResponseDTO> createMovement(
            @Valid @RequestBody InventoryMovementRequestDTO dto) {

        return new ResponseEntity<>(
                inventoryMovementService.createMovement(dto),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<InventoryMovementResponseDTO>> getAllMovements() {

        return ResponseEntity.ok(
                inventoryMovementService.getAllMovements()
        );
    }

    // GET BY ID
    @GetMapping("/{movementId}")
    public ResponseEntity<InventoryMovementResponseDTO> getMovementById(
            @PathVariable String movementId) {

        return ResponseEntity.ok(
                inventoryMovementService.getMovementById(movementId)
        );
    }

    // UPDATE
    @PutMapping("/{movementId}")
    public ResponseEntity<InventoryMovementResponseDTO> updateMovement(
            @PathVariable String movementId,
            @Valid @RequestBody InventoryMovementRequestDTO dto) {

        return ResponseEntity.ok(
                inventoryMovementService.updateMovement(
                        movementId, dto
                )
        );
    }

    // DELETE
    @DeleteMapping("/{movementId}")
    public ResponseEntity<Void> deleteMovement(
            @PathVariable String movementId) {

        inventoryMovementService.deleteMovement(movementId);

        return ResponseEntity.noContent().build();
    }
}
