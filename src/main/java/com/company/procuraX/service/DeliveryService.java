package com.company.procuraX.service;

import com.company.procuraX.dto.DeliveryRequestDTO;
import com.company.procuraX.dto.DeliveryResponseDTO;
import com.company.procuraX.exception.DeliveryNotFoundException;
import com.company.procuraX.model.Delivery;
import com.company.procuraX.repository.DeliveryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<DeliveryResponseDTO> getAllDeliveries() {

        return deliveryRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public DeliveryResponseDTO createDelivery(
            DeliveryRequestDTO request) {

        Delivery delivery = new Delivery();

        delivery.setDeliveryId(request.getDeliveryId());
        delivery.setPurchaseOrderId(request.getPurchaseOrderId());
        delivery.setDeliveryDate(request.getDeliveryDate());
        delivery.setExpectedDeliveryDate(
                request.getExpectedDeliveryDate()
        );
        delivery.setDeliveredQuantity(
                request.getDeliveredQuantity()
        );
        delivery.setAcceptedQuantity(
                request.getAcceptedQuantity()
        );
        delivery.setRejectedQuantity(
                request.getRejectedQuantity()
        );
        delivery.setStatus(request.getStatus());

        Delivery savedDelivery =
                deliveryRepository.save(delivery);

        return convertToResponseDTO(savedDelivery);
    }

    public DeliveryResponseDTO getDeliveryById(
            String deliveryId) {

        Delivery delivery =
                deliveryRepository.findById(deliveryId)
                        .orElseThrow(() ->
                                new DeliveryNotFoundException(
                                        "Delivery not found with id: "
                                                + deliveryId
                                )
                        );

        return convertToResponseDTO(delivery);
    }

    public DeliveryResponseDTO updateDelivery(
            String deliveryId,
            DeliveryRequestDTO request) {

        Delivery delivery =
                deliveryRepository.findById(deliveryId)
                        .orElseThrow(() ->
                                new DeliveryNotFoundException(
                                        "Delivery not found with id: "
                                                + deliveryId
                                )
                        );

        delivery.setPurchaseOrderId(request.getPurchaseOrderId());
        delivery.setDeliveryDate(request.getDeliveryDate());
        delivery.setExpectedDeliveryDate(
                request.getExpectedDeliveryDate()
        );
        delivery.setDeliveredQuantity(
                request.getDeliveredQuantity()
        );
        delivery.setAcceptedQuantity(
                request.getAcceptedQuantity()
        );
        delivery.setRejectedQuantity(
                request.getRejectedQuantity()
        );
        delivery.setStatus(request.getStatus());

        Delivery updatedDelivery =
                deliveryRepository.save(delivery);

        return convertToResponseDTO(updatedDelivery);
    }

    public String deleteDelivery(String deliveryId) {

        if (!deliveryRepository.existsById(deliveryId)) {

            throw new DeliveryNotFoundException(
                    "Delivery not found with id: " + deliveryId
            );
        }

        deliveryRepository.deleteById(deliveryId);

        return "Delivery deleted successfully: " + deliveryId;
    }

    private DeliveryResponseDTO convertToResponseDTO(
            Delivery delivery) {

        return new DeliveryResponseDTO(
                delivery.getDeliveryId(),
                delivery.getPurchaseOrderId(),
                delivery.getDeliveryDate(),
                delivery.getExpectedDeliveryDate(),
                delivery.getDeliveredQuantity(),
                delivery.getAcceptedQuantity(),
                delivery.getRejectedQuantity(),
                delivery.getStatus()
        );
    }
}
