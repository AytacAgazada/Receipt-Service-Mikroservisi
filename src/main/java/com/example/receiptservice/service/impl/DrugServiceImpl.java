package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.drug.DrugDTO;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.exception.ResourceNotFoundException;
import com.example.receiptservice.mapper.DrugMapper;
import com.example.receiptservice.repository.DrugRepository;
import com.example.receiptservice.service.DrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    @Override
    public DrugResponseDto createDrug(DrugDTO drugDTO) {
        log.info("Creating a new drug: {}", drugDTO.getDrugName());
        Drug drug = drugMapper.toEntity(drugDTO);
        Drug savedDrug = drugRepository.save(drug);
        log.info("Drug with ID {} created successfully.", savedDrug.getId());
        return drugMapper.toResponseDto(savedDrug);
    }

    @Override
    public DrugResponseDto getById(Long id) {
        log.info("Fetching drug with ID: {}", id);
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        return drugMapper.toResponseDto(drug);
    }

    @Override
    public List<DrugResponseDto> getByPrice(Double price) {
        log.info("Fetching drugs with price: {}", price);
        List<Drug> drugs = drugRepository.findByPrice(price);
        return drugs.stream()
                .map(drugMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrugResponseDto> getAllDrugs() {
        log.info("Fetching all drugs.");
        List<Drug> drugs = drugRepository.findAll();
        return drugs.stream()
                .map(drugMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DrugResponseDto updateDrug(Long id, DrugDTO drugDTO) {
        log.info("Updating drug with ID: {}", id);
        Drug existingDrug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));

        existingDrug.setDrugName(drugDTO.getDrugName());
        existingDrug.setDescription(drugDTO.getDescription());
        existingDrug.setInstruction(drugDTO.getInstruction());
        existingDrug.setPrice(drugDTO.getPrice());
        existingDrug.setExpirationDate(drugDTO.getExpirationDate());

        Drug updatedDrug = drugRepository.save(existingDrug);
        log.info("Drug with ID {} updated successfully.", updatedDrug.getId());
        return drugMapper.toResponseDto(updatedDrug);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting drug with ID: {}", id);
        if (!drugRepository.existsById(id)) {
            throw new ResourceNotFoundException("Drug not found with id: " + id);
        }
        drugRepository.deleteById(id);
        log.info("Drug with ID {} deleted successfully.", id);
    }
}
