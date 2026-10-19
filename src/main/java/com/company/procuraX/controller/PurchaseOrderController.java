package com.company.procuraX.controller;

import com.company.procuraX.dto.PurchaseOrderRequestDTO;
import com.company.procuraX.dto.PurchaseOrderResponseDTO;
import com.company.procuraX.service.PurchaseOrderService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService) {

        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping
    public List<PurchaseOrderResponseDTO> getAllPurchaseOrders() {

        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/{purchaseOrderId}")
    public PurchaseOrderResponseDTO getPurchaseOrderById(
            @PathVariable String purchaseOrderId) {

        return purchaseOrderService.getPurchaseOrderById(
                purchaseOrderId
        );
    }

    @PostMapping
    public PurchaseOrderResponseDTO createPurchaseOrder(
            @Valid @RequestBody PurchaseOrderRequestDTO request) {

        return purchaseOrderService.createPurchaseOrder(request);
    }

    @PutMapping("/{purchaseOrderId}")
    public PurchaseOrderResponseDTO updatePurchaseOrder(
            @PathVariable String purchaseOrderId,
            @Valid @RequestBody PurchaseOrderRequestDTO request) {

        return purchaseOrderService.updatePurchaseOrder(
                purchaseOrderId,
                request
        );
    }

    @DeleteMapping("/{purchaseOrderId}")
    public String deletePurchaseOrder(
            @PathVariable String purchaseOrderId) {

        return purchaseOrderService.deletePurchaseOrder(
                purchaseOrderId
        );
    }
}
