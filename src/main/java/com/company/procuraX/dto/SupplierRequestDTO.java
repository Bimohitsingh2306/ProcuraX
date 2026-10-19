package com.company.procuraX.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequestDTO {
    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits"
    )
    private String phone;

    @Min(value = 0, message = "Average delivery days cannot be negative")
    private int averageDeliveryDays;

    @Min(value = 0, message = "Reliability score cannot be less than 0")
    @Max(value = 100, message = "Reliability score cannot be greater than 100")
    private double reliabilityScore;
}
