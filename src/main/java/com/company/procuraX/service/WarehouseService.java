package com.company.procuraX.service;

import com.company.procuraX.dto.WarehouseRequestDTO;
import com.company.procuraX.dto.WarehouseResponseDTO;
import com.company.procuraX.exception.WarehouseNotFoundException;
import com.company.procuraX.model.Warehouse;
import com.company.procuraX.repository.WarehouseRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<WarehouseResponseDTO> getAllWarehouses() {

        return warehouseRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO request) {

        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouse(request.getWarehouse());
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setLocation(request.getLocation());
        warehouse.setCapacity(request.getCapacity());
        warehouse.setManagerName(request.getManagerName());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return convertToResponseDTO(savedWarehouse);
    }

    public WarehouseResponseDTO getWarehouseById(String warehouseId) {

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() ->
                        new WarehouseNotFoundException(
                                "Warehouse not found with id: " + warehouseId
                        )
                );

        return convertToResponseDTO(warehouse);
    }

    public WarehouseResponseDTO updateWarehouse(
            String warehouseId,
            WarehouseRequestDTO request) {

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() ->
                        new WarehouseNotFoundException(
                                "Warehouse not found with id: " + warehouseId
                        )
                );

        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setLocation(request.getLocation());
        warehouse.setCapacity(request.getCapacity());
        warehouse.setManagerName(request.getManagerName());

        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);

        return convertToResponseDTO(updatedWarehouse);
    }

    public String deleteWarehouse(String warehouseId) {

        if (!warehouseRepository.existsById(warehouseId)) {
            throw new WarehouseNotFoundException(
                    "Warehouse not found with id: " + warehouseId
            );
        }

        warehouseRepository.deleteById(warehouseId);

        return "Warehouse deleted successfully: " + warehouseId;
    }

    private WarehouseResponseDTO convertToResponseDTO(Warehouse warehouse) {

        return new WarehouseResponseDTO(
                warehouse.getWarehouse(),
                warehouse.getWarehouseName(),
                warehouse.getLocation(),
                warehouse.getCapacity(),
                warehouse.getManagerName()
        );
    }
}
