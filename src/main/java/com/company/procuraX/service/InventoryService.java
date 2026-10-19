package com.company.procuraX.service;

import com.company.procuraX.dto.InventoryRequestDTO;
import com.company.procuraX.dto.InventoryResponseDTO;
import com.company.procuraX.exception.InventoryNotFoundException;
import com.company.procuraX.model.Inventory;
import com.company.procuraX.repository.InventoryRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryResponseDTO> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public InventoryResponseDTO createInventory(
            InventoryRequestDTO request) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(request.getInventoryId());
        inventory.setProductId(request.getProductId());
        inventory.setWarehouseId(request.getWarehouseId());
        inventory.setQuantity(request.getQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());

        inventory.setLastUpdated(LocalDateTime.now());

        Inventory savedInventory =
                inventoryRepository.save(inventory);

        return convertToResponseDTO(savedInventory);
    }

    public InventoryResponseDTO getInventoryById(
            String inventoryId) {

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new InventoryNotFoundException(
                                "Inventory not found with id: " + inventoryId
                        )
                );

        return convertToResponseDTO(inventory);
    }

    public InventoryResponseDTO updateInventory(
            String inventoryId,
            InventoryRequestDTO request) {

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new InventoryNotFoundException(
                                "Inventory not found with id: " + inventoryId
                        )
                );

        inventory.setProductId(request.getProductId());
        inventory.setWarehouseId(request.getWarehouseId());
        inventory.setQuantity(request.getQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());

        inventory.setLastUpdated(LocalDateTime.now());

        Inventory updatedInventory =
                inventoryRepository.save(inventory);

        return convertToResponseDTO(updatedInventory);
    }

    public String deleteInventory(String inventoryId) {

        if (!inventoryRepository.existsById(inventoryId)) {

            throw new InventoryNotFoundException(
                    "Inventory not found with id: " + inventoryId
            );
        }

        inventoryRepository.deleteById(inventoryId);

        return "Inventory deleted successfully: " + inventoryId;
    }

    private InventoryResponseDTO convertToResponseDTO(
            Inventory inventory) {

        return new InventoryResponseDTO(
                inventory.getInventoryId(),
                inventory.getProductId(),
                inventory.getWarehouseId(),
                inventory.getQuantity(),
                inventory.getReservedQuantity(),
                inventory.getLastUpdated()
        );
    }
}
