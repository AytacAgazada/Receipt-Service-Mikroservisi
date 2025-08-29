package com.example.receiptservice.service;

import com.example.receiptservice.dto.receipt.ReceiptRequestDTO;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;

import java.util.List;

public interface ReceiptService {

    ReceiptResponseDto createReceipt(ReceiptRequestDTO receiptRequestDTO);
    ReceiptResponseDto getReceipt(Long id);
    List<ReceiptResponseDto> getReceiptsByCitizenId(Long citizenId);
    List<ReceiptResponseDto> getReceiptsByDoctorId(Long doctorId);
    List<ReceiptResponseDto> getAllReceipts();
    ReceiptResponseDto updateReceipt(Long id, ReceiptRequestDTO receiptRequestDTO);
    void deleteReceipt(Long id);
}
