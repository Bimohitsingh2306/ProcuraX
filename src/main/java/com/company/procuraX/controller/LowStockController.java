package com.company.procuraX.controller;

import com.company.procuraX.procurement.LowStockResponseDTO;
import com.company.procuraX.service.LowStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/procurement")
public class LowStockController {

    private final LowStockService lowStockService;

    public LowStockController(LowStockService lowStockService) {
        this.lowStockService = lowStockService;
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<LowStockResponseDTO>> getLowStockProducts() {

        return ResponseEntity.ok(
                lowStockService.getLowStockProducts()
        );
    }
}
