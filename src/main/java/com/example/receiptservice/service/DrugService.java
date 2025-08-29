package com.example.receiptservice.service;

import com.example.receiptservice.dto.drug.DrugDTO;
import com.example.receiptservice.dto.drug.DrugResponseDto;

import java.util.List;

public interface DrugService {

    DrugResponseDto createDrug(DrugDTO drugDTO);
    DrugResponseDto getById(Long id);
    List<DrugResponseDto> getByPrice(Double price);
    List<DrugResponseDto> getAllDrugs();
    DrugResponseDto updateDrug(Long id,DrugDTO drugDTO);
    void deleteById(Long id);
}
