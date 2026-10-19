package com.company.procuraX.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    @NotBlank(message = "Warehouse ID is required")
    private String warehouse;

    @Column(name = "warehouse_name")
    @NotBlank(message = "Warehouse name is required")
    private String warehouseName;

    @Column(name = "location")
    @NotBlank(message = "Location is required")
    private String location;

    @Column(name = "capacity")
    @Min(value = 0, message = "Capacity cannot be negative")
    private int capacity;

    @Column(name = "manager_name")
    @NotBlank(message = "Manager name is required")
    private String managerName;
}
