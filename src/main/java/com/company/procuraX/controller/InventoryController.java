package com.company.procuraX.controller;

import com.company.procuraX.dto.InventoryRequestDTO;
import com.company.procuraX.dto.InventoryResponseDTO;
import com.company.procuraX.service.InventoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryResponseDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{inventoryId}")
    public InventoryResponseDTO getInventoryById(
            @PathVariable String inventoryId) {

        return inventoryService.getInventoryById(inventoryId);
    }

    @PostMapping
    public InventoryResponseDTO createInventory(
            @Valid @RequestBody InventoryRequestDTO request) {

        return inventoryService.createInventory(request);
    }

    @PutMapping("/{inventoryId}")
    public InventoryResponseDTO updateInventory(
            @PathVariable String inventoryId,
            @Valid @RequestBody InventoryRequestDTO request) {

        return inventoryService.updateInventory(
                inventoryId,
                request
        );
    }

    @DeleteMapping("/{inventoryId}")
    public String deleteInventory(
            @PathVariable String inventoryId) {

        return inventoryService.deleteInventory(inventoryId);
    }
}
