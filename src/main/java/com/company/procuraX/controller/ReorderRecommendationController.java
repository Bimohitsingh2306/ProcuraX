package com.company.procuraX.controller;

import com.company.procuraX.procurement.ReorderRecommendationDTO;
import com.company.procuraX.service.ReorderRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/procurement")
public class ReorderRecommendationController {

    private final ReorderRecommendationService
            reorderRecommendationService;

    public ReorderRecommendationController(
            ReorderRecommendationService reorderRecommendationService) {

        this.reorderRecommendationService =
                reorderRecommendationService;
    }

    @GetMapping("/reorder-recommendations")
    public ResponseEntity<List<ReorderRecommendationDTO>>
    getReorderRecommendations() {

        return ResponseEntity.ok(
                reorderRecommendationService
                        .getReorderRecommendations()
        );
    }
}
