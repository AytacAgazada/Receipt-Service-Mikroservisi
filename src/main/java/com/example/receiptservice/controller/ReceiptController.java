package com.example.receiptservice.controller;

import com.example.receiptservice.dto.receipt.ReceiptRequestDTO;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.service.ReceiptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping
    public ResponseEntity<ReceiptResponseDto> createReceipt(@Valid @RequestBody ReceiptRequestDTO receiptRequestDTO) {
        ReceiptResponseDto createdReceipt = receiptService.createReceipt(receiptRequestDTO);
        return new ResponseEntity<>(createdReceipt, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponseDto> getReceiptById(@PathVariable Long id) {
        ReceiptResponseDto receipt = receiptService.getReceipt(id);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @GetMapping("/by-citizen/{citizenId}")
    public ResponseEntity<List<ReceiptResponseDto>> getReceiptsByCitizenId(@PathVariable Long citizenId) {
        List<ReceiptResponseDto> receipts = receiptService.getReceiptsByCitizenId(citizenId);
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<ReceiptResponseDto>> getReceiptsByDoctorId(@PathVariable Long doctorId) {
        List<ReceiptResponseDto> receipts = receiptService.getReceiptsByDoctorId(doctorId);
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptResponseDto>> getAllReceipts() {
        List<ReceiptResponseDto> receipts = receiptService.getAllReceipts();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiptResponseDto> updateReceipt(@PathVariable Long id, @Valid @RequestBody ReceiptRequestDTO receiptRequestDTO) {
        ReceiptResponseDto updatedReceipt = receiptService.updateReceipt(id, receiptRequestDTO);
        return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}