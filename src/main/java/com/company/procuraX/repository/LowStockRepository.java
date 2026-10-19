package com.company.procuraX.repository;

import com.company.procuraX.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LowStockRepository extends JpaRepository<Product, String> {

    @Query("""
            SELECT p
            FROM Product p
            WHERE p.currentStock <= p.minimumStock
            """)
    List<Product> findLowStockProducts();
}
