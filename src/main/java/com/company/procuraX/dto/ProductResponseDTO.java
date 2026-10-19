package com.company.procuraX.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ProductResponseDTO implements Serializable {

    private String productId;
    private String productName;
    private String category;
    private int currentStock;
    private int minimumStock;
    private int dailyDemand;
}
