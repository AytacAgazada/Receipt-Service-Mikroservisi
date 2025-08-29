package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.receipt.ReceiptRequestDTO;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.entity.Receipt;
import com.example.receiptservice.exception.ResourceNotFoundException;
import com.example.receiptservice.mapper.DrugMapper;
import com.example.receiptservice.mapper.ReceiptMapper;
import com.example.receiptservice.repository.ReceiptRepository;
import com.example.receiptservice.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final DrugMapper drugMapper;

    @Override
    public ReceiptResponseDto createReceipt(ReceiptRequestDTO receiptRequestDTO) {
        Receipt receipt = receiptMapper.toEntity(receiptRequestDTO);
        receipt.setCreatedAt(LocalDateTime.now()); // Reseptin yaradılma tarixini avtomatik edor

        // Drugs listində her bir derman obyekti üçün elaqeli resept
        if (receipt.getDrugs() != null) {
            for (Drug drug : receipt.getDrugs()) {
                drug.setReceipt(receipt);
            }
        }

        Receipt savedReceipt = receiptRepository.save(receipt);
        return receiptMapper.toResponseDto(savedReceipt);
    }

    @Override
    public ReceiptResponseDto getReceipt(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));
        return receiptMapper.toResponseDto(receipt);
    }

    @Override
    public List<ReceiptResponseDto> getReceiptsByCitizenId(Long citizenId) {
        List<Receipt> receipts = receiptRepository.findByCitizenId(citizenId);
        return receipts.stream()
                .map(receiptMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptResponseDto> getReceiptsByDoctorId(Long doctorId) {
        List<Receipt> receipts = receiptRepository.findByDoctorId(doctorId);
        return receipts.stream()
                .map(receiptMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptResponseDto> getAllReceipts() {
        List<Receipt> receipts = receiptRepository.findAll();
        return receipts.stream()
                .map(receiptMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptResponseDto updateReceipt(Long id, ReceiptRequestDTO requestDTO) {
        Receipt existingReceipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));

        // melumatlarin update
        existingReceipt.setCitizenId(requestDTO.getCitizenId());
        existingReceipt.setDoctorId(requestDTO.getDoctorId());

        // dermanlarin update
        existingReceipt.getDrugs().clear();
        requestDTO.getDrugs().forEach(drugDto -> {
            Drug drug = drugMapper.toEntity(drugDto);
            drug.setReceipt(existingReceipt);
            existingReceipt.getDrugs().add(drug);
        });

        Receipt updatedReceipt = receiptRepository.save(existingReceipt);
        return receiptMapper.toResponseDto(updatedReceipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        if (!receiptRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receipt not found with id: " + id);
        }
        receiptRepository.deleteById(id);
    }
}
