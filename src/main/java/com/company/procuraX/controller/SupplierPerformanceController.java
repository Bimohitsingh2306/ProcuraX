package com.company.procuraX.controller;

import com.company.procuraX.dto.SupplierPerformanceRequestDTO;
import com.company.procuraX.dto.SupplierPerformanceResponseDTO;
import com.company.procuraX.service.SupplierPerformanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier-performance")
public class SupplierPerformanceController {

    private final SupplierPerformanceService supplierPerformanceService;

    public SupplierPerformanceController(
            SupplierPerformanceService supplierPerformanceService) {

        this.supplierPerformanceService = supplierPerformanceService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<SupplierPerformanceResponseDTO> createPerformance(
            @Valid @RequestBody SupplierPerformanceRequestDTO dto) {

        return new ResponseEntity<>(
                supplierPerformanceService.createPerformance(dto),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<SupplierPerformanceResponseDTO>>
    getAllPerformance() {

        return ResponseEntity.ok(
                supplierPerformanceService.getAllPerformance()
        );
    }

    // GET BY ID
    @GetMapping("/{performanceId}")
    public ResponseEntity<SupplierPerformanceResponseDTO>
    getPerformanceById(
            @PathVariable String performanceId) {

        return ResponseEntity.ok(
                supplierPerformanceService
                        .getPerformanceById(performanceId)
        );
    }

    // UPDATE
    @PutMapping("/{performanceId}")
    public ResponseEntity<SupplierPerformanceResponseDTO>
    updatePerformance(
            @PathVariable String performanceId,
            @Valid @RequestBody SupplierPerformanceRequestDTO dto) {

        return ResponseEntity.ok(
                supplierPerformanceService.updatePerformance(
                        performanceId,
                        dto
                )
        );
    }

    // DELETE
    @DeleteMapping("/{performanceId}")
    public ResponseEntity<Void> deletePerformance(
            @PathVariable String performanceId) {

        supplierPerformanceService.deletePerformance(performanceId);

        return ResponseEntity.noContent().build();
    }

    // COMPLETION RATE
    @GetMapping("/{performanceId}/completion-rate")
    public ResponseEntity<Double> calculateCompletionRate(
            @PathVariable String performanceId) {

        return ResponseEntity.ok(
                supplierPerformanceService
                        .calculateCompletionRate(performanceId)
        );
    }

    // LATE ORDER RATE
    @GetMapping("/{performanceId}/late-order-rate")
    public ResponseEntity<Double> calculateLateOrderRate(
            @PathVariable String performanceId) {

        return ResponseEntity.ok(
                supplierPerformanceService
                        .calculateLateOrderRate(performanceId)
        );
    }
}
