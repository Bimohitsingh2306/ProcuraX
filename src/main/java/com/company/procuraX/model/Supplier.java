package com.company.procuraX.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    @Column(name = "supplier_name")
    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @Column(name = "email")
    @NotBlank(message = "email is required")
    @Email(message = "Invalid email")
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits"
    )
    private String phone;

    @Column(name = "average_delivery_days")
    @Min(value = 0, message = "Average delivery days cannot be negative")
    private int averageDeliveryDays;

    @Column(name = "reliability_score")
    @Min(value = 0, message = "Reliability score cannot be less than 0")
    @Max(value = 100, message = "Reliability score cannot be greater than 100")
    private double reliabilityScore;
}
