package com.company.procuraX.service;

import com.company.procuraX.dto.PurchaseOrderRequestDTO;
import com.company.procuraX.dto.PurchaseOrderResponseDTO;
import com.company.procuraX.exception.PurchaseOrderNotFoundException;
import com.company.procuraX.model.PurchaseOrder;
import com.company.procuraX.repository.PurchaseOrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository) {

        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrderResponseDTO> getAllPurchaseOrders() {

        return purchaseOrderRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public PurchaseOrderResponseDTO createPurchaseOrder(
            PurchaseOrderRequestDTO request) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setPurchaseOrderId(request.getPurchaseOrderId());
        purchaseOrder.setSupplierId(request.getSupplierId());
        purchaseOrder.setProductId(request.getProductId());
        purchaseOrder.setQuantity(request.getQuantity());
        purchaseOrder.setOrderDate(request.getOrderDate());
        purchaseOrder.setExpectedDeliveryDate(
                request.getExpectedDeliveryDate()
        );
        purchaseOrder.setStatus(request.getStatus());
        purchaseOrder.setUnitPrice(request.getUnitPrice());

        PurchaseOrder savedPurchaseOrder =
                purchaseOrderRepository.save(purchaseOrder);

        return convertToResponseDTO(savedPurchaseOrder);
    }

    public PurchaseOrderResponseDTO getPurchaseOrderById(
            String purchaseOrderId) {

        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(purchaseOrderId)
                        .orElseThrow(() ->
                                new PurchaseOrderNotFoundException(
                                        "Purchase Order not found with id: "
                                                + purchaseOrderId
                                )
                        );

        return convertToResponseDTO(purchaseOrder);
    }

    public PurchaseOrderResponseDTO updatePurchaseOrder(
            String purchaseOrderId,
            PurchaseOrderRequestDTO request) {

        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(purchaseOrderId)
                        .orElseThrow(() ->
                                new PurchaseOrderNotFoundException(
                                        "Purchase Order not found with id: "
                                                + purchaseOrderId
                                )
                        );

        purchaseOrder.setSupplierId(request.getSupplierId());
        purchaseOrder.setProductId(request.getProductId());
        purchaseOrder.setQuantity(request.getQuantity());
        purchaseOrder.setOrderDate(request.getOrderDate());
        purchaseOrder.setExpectedDeliveryDate(
                request.getExpectedDeliveryDate()
        );
        purchaseOrder.setStatus(request.getStatus());
        purchaseOrder.setUnitPrice(request.getUnitPrice());

        PurchaseOrder updatedPurchaseOrder =
                purchaseOrderRepository.save(purchaseOrder);

        return convertToResponseDTO(updatedPurchaseOrder);
    }

    public String deletePurchaseOrder(String purchaseOrderId) {

        if (!purchaseOrderRepository.existsById(purchaseOrderId)) {

            throw new PurchaseOrderNotFoundException(
                    "Purchase Order not found with id: "
                            + purchaseOrderId
            );
        }

        purchaseOrderRepository.deleteById(purchaseOrderId);

        return "Purchase Order deleted successfully: "
                + purchaseOrderId;
    }

    private PurchaseOrderResponseDTO convertToResponseDTO(
            PurchaseOrder purchaseOrder) {

        return new PurchaseOrderResponseDTO(
                purchaseOrder.getPurchaseOrderId(),
                purchaseOrder.getSupplierId(),
                purchaseOrder.getProductId(),
                purchaseOrder.getQuantity(),
                purchaseOrder.getOrderDate(),
                purchaseOrder.getExpectedDeliveryDate(),
                purchaseOrder.getStatus(),
                purchaseOrder.getUnitPrice()
        );
    }
}
