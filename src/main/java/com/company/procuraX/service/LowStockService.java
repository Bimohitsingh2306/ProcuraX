package com.company.procuraX.service;

import com.company.procuraX.model.Product;
import com.company.procuraX.procurement.LowStockResponseDTO;
import com.company.procuraX.repository.LowStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LowStockService {

    private final LowStockRepository lowStockRepository;

    public LowStockService(LowStockRepository lowStockRepository) {
        this.lowStockRepository = lowStockRepository;
    }

    public List<LowStockResponseDTO> getLowStockProducts() {

        List<Product> products =
                lowStockRepository.findLowStockProducts();

        return products.stream()
                .map(product -> new LowStockResponseDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getCurrentStock(),
                        product.getMinimumStock(),
                        product.getDailyDemand(),
                        "LOW_STOCK"
                ))
                .toList();
    }
}
