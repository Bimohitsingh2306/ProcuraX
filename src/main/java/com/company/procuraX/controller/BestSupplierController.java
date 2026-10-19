package com.company.procuraX.controller;

import com.company.procuraX.procurement.BestSupplierResponseDTO;
import com.company.procuraX.service.BestSupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/procurement")
public class BestSupplierController {

    private final BestSupplierService bestSupplierService;

    public BestSupplierController(
            BestSupplierService bestSupplierService) {
        this.bestSupplierService = bestSupplierService;
    }

    @GetMapping("/best-suppliers")
    public ResponseEntity<List<BestSupplierResponseDTO>> getBestSuppliers() {

        return ResponseEntity.ok(
                bestSupplierService.getBestSuppliers()
        );
    }
}
