package com.company.procuraX.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WarehouseResponseDTO {

    private String warehouse;
    private String warehouseName;
    private String location;
    private int capacity;
    private String managerName;
}
