package com.company.procuraX.service;

import com.company.procuraX.dto.SupplierRequestDTO;
import com.company.procuraX.dto.SupplierResponseDTO;
import com.company.procuraX.exception.SupplierNotFoundException;
import com.company.procuraX.model.Supplier;
import com.company.procuraX.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierResponseDTO> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public SupplierResponseDTO createSupplier(SupplierRequestDTO request) {

        Supplier supplier = new Supplier();

        supplier.setSupplierId(request.getSupplierId());
        supplier.setSupplierName(request.getSupplierName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAverageDeliveryDays(request.getAverageDeliveryDays());
        supplier.setReliabilityScore(request.getReliabilityScore());

        Supplier savedSupplier = supplierRepository.save(supplier);

        return convertToResponseDTO(savedSupplier);
    }

    public SupplierResponseDTO getSupplierById(String supplierId) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id: " + supplierId
                        )
                );

        return convertToResponseDTO(supplier);
    }

    public SupplierResponseDTO updateSupplier(
            String supplierId,
            SupplierRequestDTO request) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id: " + supplierId
                        )
                );

        supplier.setSupplierName(request.getSupplierName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAverageDeliveryDays(request.getAverageDeliveryDays());
        supplier.setReliabilityScore(request.getReliabilityScore());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return convertToResponseDTO(updatedSupplier);
    }

    public String deleteSupplier(String supplierId) {

        if (!supplierRepository.existsById(supplierId)) {
            throw new SupplierNotFoundException(
                    "Supplier not found with id: " + supplierId
            );
        }
        supplierRepository.deleteById(supplierId);

        return "Supplier deleted successfully: " + supplierId;
    }
    private SupplierResponseDTO convertToResponseDTO(Supplier supplier) {

        return new SupplierResponseDTO(
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAverageDeliveryDays(),
                supplier.getReliabilityScore()
        );
    }
}