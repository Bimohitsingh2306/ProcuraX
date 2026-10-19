package com.company.procuraX.service;

import com.company.procuraX.model.Product;
import com.company.procuraX.procurement.ReorderRecommendationDTO;
import com.company.procuraX.repository.LowStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReorderRecommendationService {

    private final LowStockRepository lowStockRepository;

    public ReorderRecommendationService(
            LowStockRepository lowStockRepository) {
        this.lowStockRepository = lowStockRepository;
    }

    public List<ReorderRecommendationDTO> getReorderRecommendations() {

        List<Product> lowStockProducts =
                lowStockRepository.findLowStockProducts();

        return lowStockProducts.stream()
                .map(this::createRecommendation)
                .toList();
    }

    private ReorderRecommendationDTO createRecommendation(
            Product product) {

        int recommendedQuantity =
                (product.getMinimumStock()
                        + (product.getDailyDemand() * 7))
                        - product.getCurrentStock();

        // Safety check
        if (recommendedQuantity < 0) {
            recommendedQuantity = 0;
        }

        String reason =
                "Stock is below minimum level. "
                        + "Recommended quantity includes "
                        + "7 days of expected demand.";

        return new ReorderRecommendationDTO(
                product.getProductId(),
                product.getProductName(),
                product.getCurrentStock(),
                product.getMinimumStock(),
                product.getDailyDemand(),
                recommendedQuantity,
                reason
        );
    }
}
