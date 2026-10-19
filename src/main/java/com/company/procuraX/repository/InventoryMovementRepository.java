package com.company.procuraX.repository;

import com.company.procuraX.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository
        extends JpaRepository<InventoryMovement, String> {
}
