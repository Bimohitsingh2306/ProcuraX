package com.company.procuraX.repository;

import com.company.procuraX.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository
        extends JpaRepository<PurchaseOrder, String> {

}