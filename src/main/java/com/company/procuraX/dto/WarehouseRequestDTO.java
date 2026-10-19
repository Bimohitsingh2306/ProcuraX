package com.company.procuraX.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseRequestDTO {

    @NotBlank(message = "Warehouse ID is required")
    private String warehouse;

    @NotBlank(message = "Warehouse name is required")
    private String warehouseName;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 0, message = "Capacity cannot be negative")
    private int capacity;

    @NotBlank(message = "Manager name is required")
    private String managerName;
}
