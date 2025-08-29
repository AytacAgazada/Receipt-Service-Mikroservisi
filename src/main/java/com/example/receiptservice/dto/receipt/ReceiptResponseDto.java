package com.example.receiptservice.dto.receipt;

import com.example.receiptservice.entity.Drug;
import lombok.Data;

import java.util.List;

@Data
public class ReceiptResponseDto {
    private Long id;
    private Long citizenId;
    private Long doctorId;
    private String createdAt;
    private List<Drug> drugs;
}
