package com.example.receiptservice.repository;

import com.example.receiptservice.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {


    List<Drug> findByPrice(Double price);
}
