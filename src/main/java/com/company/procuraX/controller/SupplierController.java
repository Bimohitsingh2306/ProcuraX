package com.company.procuraX.controller;

import com.company.procuraX.dto.SupplierRequestDTO;
import com.company.procuraX.dto.SupplierResponseDTO;
import com.company.procuraX.service.SupplierService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{supplierId}")
    public SupplierResponseDTO getSupplierById(
            @PathVariable String supplierId) {

        return supplierService.getSupplierById(supplierId);
    }

    @PostMapping
    public SupplierResponseDTO createSupplier(
            @Valid @RequestBody SupplierRequestDTO request) {

        return supplierService.createSupplier(request);
    }

    @PutMapping("/{supplierId}")
    public SupplierResponseDTO updateSupplier(
            @PathVariable String supplierId,
            @Valid @RequestBody SupplierRequestDTO request) {

        return supplierService.updateSupplier(supplierId, request);
    }

    @DeleteMapping("/{supplierId}")
    public String deleteSupplier(
            @PathVariable String supplierId) {

        return supplierService.deleteSupplier(supplierId);
    }
}
