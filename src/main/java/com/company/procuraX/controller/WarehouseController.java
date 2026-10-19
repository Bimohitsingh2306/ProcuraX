package com.company.procuraX.controller;

import com.company.procuraX.dto.WarehouseRequestDTO;
import com.company.procuraX.dto.WarehouseResponseDTO;
import com.company.procuraX.service.WarehouseService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{warehouseId}")
    public WarehouseResponseDTO getWarehouseById(
            @PathVariable String warehouseId) {

        return warehouseService.getWarehouseById(warehouseId);
    }

    @PostMapping
    public WarehouseResponseDTO createWarehouse(
            @Valid @RequestBody WarehouseRequestDTO request) {

        return warehouseService.createWarehouse(request);
    }

    @PutMapping("/{warehouseId}")
    public WarehouseResponseDTO updateWarehouse(
            @PathVariable String warehouseId,
            @Valid @RequestBody WarehouseRequestDTO request) {

        return warehouseService.updateWarehouse(warehouseId, request);
    }

    @DeleteMapping("/{warehouseId}")
    public String deleteWarehouse(
            @PathVariable String warehouseId) {

        return warehouseService.deleteWarehouse(warehouseId);
    }
}
