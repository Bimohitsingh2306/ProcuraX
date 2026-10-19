
package com.company.procuraX.dto;

public class DashboardSummaryDTO {

    private long totalSuppliers;
    private long totalProducts;
    private long totalWarehouses;
    private long totalInventoryItems;
    private long lowStockProducts;
    private long totalPurchaseOrders;
    private long totalDeliveries;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(
            long totalSuppliers,
            long totalProducts,
            long totalWarehouses,
            long totalInventoryItems,
            long lowStockProducts,
            long totalPurchaseOrders,
            long totalDeliveries) {

        this.totalSuppliers = totalSuppliers;
        this.totalProducts = totalProducts;
        this.totalWarehouses = totalWarehouses;
        this.totalInventoryItems = totalInventoryItems;
        this.lowStockProducts = lowStockProducts;
        this.totalPurchaseOrders = totalPurchaseOrders;
        this.totalDeliveries = totalDeliveries;
    }

    public long getTotalSuppliers() {
        return totalSuppliers;
    }

    public void setTotalSuppliers(long totalSuppliers) {
        this.totalSuppliers = totalSuppliers;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalWarehouses() {
        return totalWarehouses;
    }

    public void setTotalWarehouses(long totalWarehouses) {
        this.totalWarehouses = totalWarehouses;
    }

    public long getTotalInventoryItems() {
        return totalInventoryItems;
    }

    public void setTotalInventoryItems(long totalInventoryItems) {
        this.totalInventoryItems = totalInventoryItems;
    }

    public long getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(long lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public long getTotalPurchaseOrders() {
        return totalPurchaseOrders;
    }

    public void setTotalPurchaseOrders(long totalPurchaseOrders) {
        this.totalPurchaseOrders = totalPurchaseOrders;
    }

    public long getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(long totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }
}
