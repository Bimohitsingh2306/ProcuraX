package com.company.procuraX.service;

import com.company.procuraX.dto.SupplierPerformanceRequestDTO;
import com.company.procuraX.dto.SupplierPerformanceResponseDTO;
import com.company.procuraX.exception.SupplierPerformanceNotFoundException;
import com.company.procuraX.model.SupplierPerformance;
import com.company.procuraX.repository.SupplierPerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierPerformanceService {

    private final SupplierPerformanceRepository supplierPerformanceRepository;

    public SupplierPerformanceService(
            SupplierPerformanceRepository supplierPerformanceRepository) {

        this.supplierPerformanceRepository = supplierPerformanceRepository;
    }

    // CREATE
    public SupplierPerformanceResponseDTO createPerformance(
            SupplierPerformanceRequestDTO dto) {

        SupplierPerformance performance = new SupplierPerformance();

        performance.setPerformanceId(dto.getPerformanceId());
        performance.setSupplierId(dto.getSupplierId());
        performance.setTotalOrders(dto.getTotalOrders());
        performance.setCompletedOrders(dto.getCompletedOrders());
        performance.setLateOrders(dto.getLateOrders());
        performance.setAverageDelayDays(dto.getAverageDelayDays());
        performance.setQualityScore(dto.getQualityScore());
        performance.setReliabilityScore(dto.getReliabilityScore());

        SupplierPerformance saved =
                supplierPerformanceRepository.save(performance);

        return mapToResponseDTO(saved);
    }

    // GET ALL
    public List<SupplierPerformanceResponseDTO> getAllPerformance() {

        return supplierPerformanceRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // GET BY ID
    public SupplierPerformanceResponseDTO getPerformanceById(
            String performanceId) {

        SupplierPerformance performance =
                supplierPerformanceRepository.findById(performanceId)
                        .orElseThrow(() ->
                                new SupplierPerformanceNotFoundException(
                                        "Supplier performance not found with ID: "
                                                + performanceId));

        return mapToResponseDTO(performance);
    }

    // UPDATE
    public SupplierPerformanceResponseDTO updatePerformance(
            String performanceId,
            SupplierPerformanceRequestDTO dto) {

        SupplierPerformance performance =
                supplierPerformanceRepository.findById(performanceId)
                        .orElseThrow(() ->
                                new SupplierPerformanceNotFoundException(
                                        "Supplier performance not found with ID: "
                                                + performanceId));

        performance.setSupplierId(dto.getSupplierId());
        performance.setTotalOrders(dto.getTotalOrders());
        performance.setCompletedOrders(dto.getCompletedOrders());
        performance.setLateOrders(dto.getLateOrders());
        performance.setAverageDelayDays(dto.getAverageDelayDays());
        performance.setQualityScore(dto.getQualityScore());
        performance.setReliabilityScore(dto.getReliabilityScore());

        SupplierPerformance updated =
                supplierPerformanceRepository.save(performance);

        return mapToResponseDTO(updated);
    }

    // DELETE
    public void deletePerformance(String performanceId) {

        if (!supplierPerformanceRepository.existsById(performanceId)) {

            throw new SupplierPerformanceNotFoundException(
                    "Supplier performance not found with ID: "
                            + performanceId);
        }

        supplierPerformanceRepository.deleteById(performanceId);
    }

    // ENTITY -> DTO
    private SupplierPerformanceResponseDTO mapToResponseDTO(
            SupplierPerformance performance) {

        return new SupplierPerformanceResponseDTO(
                performance.getPerformanceId(),
                performance.getSupplierId(),
                performance.getTotalOrders(),
                performance.getCompletedOrders(),
                performance.getLateOrders(),
                performance.getAverageDelayDays(),
                performance.getQualityScore(),
                performance.getReliabilityScore()
        );
    }

    // BUSINESS LOGIC
    public double calculateCompletionRate(String performanceId) {

        SupplierPerformance performance =
                supplierPerformanceRepository.findById(performanceId)
                        .orElseThrow(() ->
                                new SupplierPerformanceNotFoundException(
                                        "Supplier performance not found with ID: "
                                                + performanceId));

        if (performance.getTotalOrders() == 0) {
            return 0;
        }

        return (performance.getCompletedOrders() * 100.0)
                / performance.getTotalOrders();
    }

    public double calculateLateOrderRate(String performanceId) {

        SupplierPerformance performance =
                supplierPerformanceRepository.findById(performanceId)
                        .orElseThrow(() ->
                                new SupplierPerformanceNotFoundException(
                                        "Supplier performance not found with ID: "
                                                + performanceId));

        if (performance.getTotalOrders() == 0) {
            return 0;
        }

        return (performance.getLateOrders() * 100.0)
                / performance.getTotalOrders();
    }
}
