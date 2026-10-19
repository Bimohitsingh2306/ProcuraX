package com.company.procuraX.service;

import com.company.procuraX.dto.ProductRequestDTO;
import com.company.procuraX.dto.ProductResponseDTO;
import com.company.procuraX.exception.ProductNotFoundException;
import com.company.procuraX.model.Product;
import com.company.procuraX.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AuditLogService auditLogService;

    public ProductService(
            ProductRepository productRepository,
            AuditLogService auditLogService) {

        this.productRepository = productRepository;
        this.auditLogService = auditLogService;
    }
    @Cacheable("products")
    public List<ProductResponseDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @CacheEvict(value = "products", allEntries = true)
    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        Product product = new Product();

        product.setProductId(request.getProductId());
        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setCurrentStock(request.getCurrentStock());
        product.setMinimumStock(request.getMinimumStock());
        product.setDailyDemand(request.getDailyDemand());

        Product savedProduct = productRepository.save(product);
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        auditLogService.logAction(
                username,
                "CREATE",
                "Product",
                savedProduct.getProductId(),
                "Product created: " + savedProduct.getProductName()
        );
        return convertToResponseDTO(savedProduct);
    }

    public ProductResponseDTO getProductById(String productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        return convertToResponseDTO(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public ProductResponseDTO updateProduct(
            String productId,
            ProductRequestDTO request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + productId
                        )
                );

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setCurrentStock(request.getCurrentStock());
        product.setMinimumStock(request.getMinimumStock());
        product.setDailyDemand(request.getDailyDemand());

        Product updatedProduct = productRepository.save(product);
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        auditLogService.logAction(
                username,
                "UPDATE",
                "Product",
                updatedProduct.getProductId(),
                "Product updated: " + updatedProduct.getProductName()
        );
        return convertToResponseDTO(updatedProduct);
    }

    @CacheEvict(value = "products", allEntries = true)
    public String deleteProduct(String productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + productId
            );
        }

        productRepository.deleteById(productId);
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        auditLogService.logAction(
                username,
                "DELETE",
                "Product",
                productId,
                "Product deleted"
        );
        return "Product deleted successfully: " + productId;
    }

    private ProductResponseDTO convertToResponseDTO(Product product) {

        return new ProductResponseDTO(
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getCurrentStock(),
                product.getMinimumStock(),
                product.getDailyDemand()
        );
    }
}