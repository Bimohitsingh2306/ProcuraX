package com.company.procuraX.controller;

import com.company.procuraX.dto.DeliveryRequestDTO;
import com.company.procuraX.dto.DeliveryResponseDTO;
import com.company.procuraX.service.DeliveryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryResponseDTO> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{deliveryId}")
    public DeliveryResponseDTO getDeliveryById(
            @PathVariable String deliveryId) {

        return deliveryService.getDeliveryById(deliveryId);
    }

    @PostMapping
    public DeliveryResponseDTO createDelivery(
            @Valid @RequestBody DeliveryRequestDTO request) {

        return deliveryService.createDelivery(request);
    }

    @PutMapping("/{deliveryId}")
    public DeliveryResponseDTO updateDelivery(
            @PathVariable String deliveryId,
            @Valid @RequestBody DeliveryRequestDTO request) {

        return deliveryService.updateDelivery(
                deliveryId,
                request
        );
    }

    @DeleteMapping("/{deliveryId}")
    public String deleteDelivery(
            @PathVariable String deliveryId) {

        return deliveryService.deleteDelivery(deliveryId);
    }
}
