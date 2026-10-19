package com.company.procuraX.service;

import com.company.procuraX.model.Supplier;
import com.company.procuraX.model.SupplierPerformance;
import com.company.procuraX.procurement.BestSupplierResponseDTO;
import com.company.procuraX.repository.SupplierPerformanceRepository;
import com.company.procuraX.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestSupplierService {

    private final SupplierPerformanceRepository supplierPerformanceRepository;
    private final SupplierRepository supplierRepository;

    public BestSupplierService(
            SupplierPerformanceRepository supplierPerformanceRepository,
            SupplierRepository supplierRepository) {

        this.supplierPerformanceRepository = supplierPerformanceRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<BestSupplierResponseDTO> getBestSuppliers() {

        List<SupplierPerformance> performances =
                supplierPerformanceRepository.findBestSuppliers();

        return performances.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private BestSupplierResponseDTO convertToDTO(
            SupplierPerformance performance) {

        Supplier supplier = supplierRepository
                .findById(performance.getSupplierId())
                .orElse(null);

        String supplierName =
                supplier != null
                        ? supplier.getSupplierName()
                        : "Unknown Supplier";

        String reason =
                "High reliability and quality score with low average delay.";

        return new BestSupplierResponseDTO(
                performance.getSupplierId(),
                supplierName,
                performance.getReliabilityScore(),
                performance.getQualityScore(),
                performance.getAverageDelayDays(),
                reason
        );
    }
}
