
package com.company.procuraX.service;

import com.company.procuraX.dto.DashboardSummaryDTO;
import com.company.procuraX.repository.SupplierRepository;
import com.company.procuraX.repository.ProductRepository;
import com.company.procuraX.repository.WarehouseRepository;
import com.company.procuraX.repository.InventoryRepository;
import com.company.procuraX.repository.PurchaseOrderRepository;
import com.company.procuraX.repository.DeliveryRepository;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRepository inventoryRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final DeliveryRepository deliveryRepository;

    public DashboardService(
            SupplierRepository supplierRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository,
            InventoryRepository inventoryRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            DeliveryRepository deliveryRepository) {

        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.inventoryRepository = inventoryRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public DashboardSummaryDTO getDashboardSummary() {

        long totalSuppliers = supplierRepository.count();
        long totalProducts = productRepository.count();
        long totalWarehouses = warehouseRepository.count();
        long totalInventoryItems = inventoryRepository.count();
        long totalPurchaseOrders = purchaseOrderRepository.count();
        long totalDeliveries = deliveryRepository.count();

        long lowStockProducts = productRepository
                .findAll()
                .stream()
                .filter(product ->
                        product.getCurrentStock() < product.getMinimumStock())
                .count();

        return new DashboardSummaryDTO(
                totalSuppliers,
                totalProducts,
                totalWarehouses,
                totalInventoryItems,
                lowStockProducts,
                totalPurchaseOrders,
                totalDeliveries
        );
    }
}
