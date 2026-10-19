package com.company.procuraX.service;

import com.company.procuraX.model.Product;
import com.company.procuraX.model.Supplier;
import com.company.procuraX.model.SupplierPerformance;
import com.company.procuraX.procurement.SmartProcurementRecommendationDTO;
import com.company.procuraX.repository.LowStockRepository;
import com.company.procuraX.repository.SupplierPerformanceRepository;
import com.company.procuraX.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartProcurementService {

    private final LowStockRepository lowStockRepository;
    private final SupplierPerformanceRepository supplierPerformanceRepository;
    private final SupplierRepository supplierRepository;

    public SmartProcurementService(
            LowStockRepository lowStockRepository,
            SupplierPerformanceRepository supplierPerformanceRepository,
            SupplierRepository supplierRepository) {

        this.lowStockRepository = lowStockRepository;
        this.supplierPerformanceRepository = supplierPerformanceRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<SmartProcurementRecommendationDTO>
    getSmartProcurementRecommendations() {

        List<Product> lowStockProducts =
                lowStockRepository.findLowStockProducts();

        List<SupplierPerformance> supplierPerformances =
                supplierPerformanceRepository.findBestSuppliers();

        return lowStockProducts.stream()
                .map(product ->
                        createRecommendation(product, supplierPerformances))
                .toList();
    }

    private SmartProcurementRecommendationDTO createRecommendation(
            Product product,
            List<SupplierPerformance> supplierPerformances) {

        int recommendedQuantity =
                (product.getMinimumStock()
                        + (product.getDailyDemand() * 7))
                        - product.getCurrentStock();

        if (recommendedQuantity < 0) {
            recommendedQuantity = 0;
        }

        SupplierPerformance bestPerformance =
                supplierPerformances.isEmpty()
                        ? null
                        : supplierPerformances.get(0);

        String supplierId = "N/A";
        String supplierName = "No supplier available";
        double reliabilityScore = 0;
        double qualityScore = 0;
        double averageDelayDays = 0;

        if (bestPerformance != null) {

            supplierId = bestPerformance.getSupplierId();

            Supplier supplier = supplierRepository
                    .findById(supplierId)
                    .orElse(null);

            if (supplier != null) {
                supplierName = supplier.getSupplierName();
            }

            reliabilityScore =
                    bestPerformance.getReliabilityScore();

            qualityScore =
                    bestPerformance.getQualityScore();

            averageDelayDays =
                    bestPerformance.getAverageDelayDays();
        }

        String recommendationReason =
                "Product is below minimum stock. "
                        + "Reorder quantity covers minimum stock "
                        + "plus 7 days of expected demand. "
                        + "Supplier selected based on reliability, "
                        + "quality and delivery performance.";

        return new SmartProcurementRecommendationDTO(
                product.getProductId(),
                product.getProductName(),
                product.getCurrentStock(),
                product.getMinimumStock(),
                product.getDailyDemand(),
                recommendedQuantity,
                supplierId,
                supplierName,
                reliabilityScore,
                qualityScore,
                averageDelayDays,
                recommendationReason
        );
    }
}
