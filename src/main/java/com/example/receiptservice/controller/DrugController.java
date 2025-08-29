package com.example.receiptservice.controller;

import com.example.receiptservice.dto.drug.DrugDTO;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.service.DrugService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @PostMapping
    public ResponseEntity<DrugResponseDto> createDrug(@Valid @RequestBody DrugDTO drugDTO) {
        DrugResponseDto createdDrug = drugService.createDrug(drugDTO);
        return new ResponseEntity<>(createdDrug, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugResponseDto> getDrugById(@PathVariable Long id) {
        DrugResponseDto drug = drugService.getById(id);
        return new ResponseEntity<>(drug, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DrugResponseDto>> getAllDrugs() {
        List<DrugResponseDto> drugs = drugService.getAllDrugs();
        return new ResponseEntity<>(drugs, HttpStatus.OK);
    }

    @GetMapping("/by-price")
    public ResponseEntity<List<DrugResponseDto>> getDrugsByPrice(@RequestParam Double price) {
        List<DrugResponseDto> drugs = drugService.getByPrice(price);
        return new ResponseEntity<>(drugs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugResponseDto> updateDrug(@PathVariable Long id, @Valid @RequestBody DrugDTO drugDTO) {
        DrugResponseDto updatedDrug = drugService.updateDrug(id, drugDTO);
        return new ResponseEntity<>(updatedDrug, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long id) {
        drugService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}