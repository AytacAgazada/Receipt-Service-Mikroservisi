package com.example.receiptservice.repository;

import com.example.receiptservice.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByCitizenId(Long citizenId);
    List<Receipt> findByDoctorId(Long doctorId);

}
