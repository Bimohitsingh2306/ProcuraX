
package com.company.procuraX.repository;

import com.company.procuraX.model.SupplierPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierPerformanceRepository
        extends JpaRepository<SupplierPerformance, String> {

    @Query("""
            SELECT sp
            FROM SupplierPerformance sp
            ORDER BY sp.reliabilityScore DESC,
                     sp.qualityScore DESC,
                     sp.averageDelayDays ASC
            """)
    List<SupplierPerformance> findBestSuppliers();
}