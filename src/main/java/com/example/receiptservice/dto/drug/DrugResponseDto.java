package com.example.receiptservice.dto.drug;

import com.example.receiptservice.entity.Receipt;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DrugResponseDto {
    private Long id;
    private String drugName;
    private String description;
    private String instruction;
    private Double price;
    private LocalDate expirationDate;
}
