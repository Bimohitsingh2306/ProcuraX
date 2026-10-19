package com.company.procuraX.service;

import com.company.procuraX.dto.InventoryMovementRequestDTO;
import com.company.procuraX.dto.InventoryMovementResponseDTO;
import com.company.procuraX.exception.InventoryMovementNotFoundException;
import com.company.procuraX.model.InventoryMovement;
import com.company.procuraX.repository.InventoryMovementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovementService(InventoryMovementRepository inventoryMovementRepository) {
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    // CREATE
    public InventoryMovementResponseDTO createMovement(
            InventoryMovementRequestDTO dto) {

        InventoryMovement movement = new InventoryMovement();

        movement.setMovementId(dto.getMovementId());
        movement.setProductId(dto.getProductId());
        movement.setWarehouseId(dto.getWarehouseId());
        movement.setMovementType(dto.getMovementType());
        movement.setQuantity(dto.getQuantity());
        movement.setReferenceId(dto.getReferenceId());

        // Automatically set current date/time
        movement.setMovementDate(LocalDateTime.now());

        InventoryMovement saved =
                inventoryMovementRepository.save(movement);

        return mapToResponseDTO(saved);
    }

    // GET ALL
    public List<InventoryMovementResponseDTO> getAllMovements() {

        return inventoryMovementRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // GET BY ID
    public InventoryMovementResponseDTO getMovementById(String movementId) {

        InventoryMovement movement =
                inventoryMovementRepository.findById(movementId)
                        .orElseThrow(() ->
                                new InventoryMovementNotFoundException(
                                        "Inventory movement not found with ID: "
                                                + movementId));

        return mapToResponseDTO(movement);
    }

    // UPDATE
    public InventoryMovementResponseDTO updateMovement(
            String movementId,
            InventoryMovementRequestDTO dto) {

        InventoryMovement movement =
                inventoryMovementRepository.findById(movementId)
                        .orElseThrow(() ->
                                new InventoryMovementNotFoundException(
                                        "Inventory movement not found with ID: "
                                                + movementId));

        movement.setProductId(dto.getProductId());
        movement.setWarehouseId(dto.getWarehouseId());
        movement.setMovementType(dto.getMovementType());
        movement.setQuantity(dto.getQuantity());
        movement.setReferenceId(dto.getReferenceId());

        InventoryMovement updated =
                inventoryMovementRepository.save(movement);

        return mapToResponseDTO(updated);
    }

    // DELETE
    public void deleteMovement(String movementId) {

        if (!inventoryMovementRepository.existsById(movementId)) {
            throw new InventoryMovementNotFoundException(
                    "Inventory movement not found with ID: " + movementId);
        }

        inventoryMovementRepository.deleteById(movementId);
    }

    // ENTITY -> RESPONSE DTO
    private InventoryMovementResponseDTO mapToResponseDTO(
            InventoryMovement movement) {

        return new InventoryMovementResponseDTO(
                movement.getMovementId(),
                movement.getProductId(),
                movement.getWarehouseId(),
                movement.getMovementType(),
                movement.getQuantity(),
                movement.getReferenceId(),
                movement.getMovementDate()
        );
    }
}
