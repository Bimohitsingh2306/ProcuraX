package com.company.procuraX.controller;

import com.company.procuraX.procurement.SmartProcurementRecommendationDTO;
import com.company.procuraX.service.SmartProcurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/procurement")
public class SmartProcurementController {

    private final SmartProcurementService smartProcurementService;

    public SmartProcurementController(
            SmartProcurementService smartProcurementService) {

        this.smartProcurementService = smartProcurementService;
    }

    @GetMapping("/smart-recommendations")
    public ResponseEntity<List<SmartProcurementRecommendationDTO>>
    getSmartProcurementRecommendations() {

        return ResponseEntity.ok(
                smartProcurementService
                        .getSmartProcurementRecommendations()
        );
    }
}
