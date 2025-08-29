package com.example.receiptservice.dto.receipt;

import com.example.receiptservice.dto.drug.DrugDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceiptRequestDTO {

    @NotNull(message = "Citizen ID is required")
    private Long citizenId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Created date is required")
    private LocalDateTime createdAt;

    @NotEmpty(message = "At least one drug is required")
    @Valid // nested validation üçün
    private List<DrugDTO> drugs;
}
