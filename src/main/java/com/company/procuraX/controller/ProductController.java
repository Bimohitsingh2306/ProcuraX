package com.company.procuraX.controller;

import com.company.procuraX.dto.ProductRequestDTO;
import com.company.procuraX.dto.ProductResponseDTO;
import com.company.procuraX.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponseDTO getProductById(
            @PathVariable String productId) {

        return productService.getProductById(productId);
    }

    @PostMapping
    public ProductResponseDTO createProduct(
            @Valid @RequestBody ProductRequestDTO request) {

        return productService.createProduct(request);
    }

    @PutMapping("/{productId}")
    public ProductResponseDTO updateProduct(
            @PathVariable String productId,
            @Valid @RequestBody ProductRequestDTO request) {

        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(
            @PathVariable String productId) {

        return productService.deleteProduct(productId);
    }
}
