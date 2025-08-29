package com.example.receiptservice.dto.drug;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DrugDTO {

    @NotBlank(message = "Drug name is required")
    @Size(max = 100, message = "Drug name cannot exceed 100 characters")
    private String drugName;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Instruction is required")
    @Size(max = 500, message = "Instruction cannot exceed 500 characters")
    private String instruction;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Expiration date is required")
    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;
}
