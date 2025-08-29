package com.example.receiptservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@SuppressWarnings("JpaAttributeTypeInspection")//sadece receipt_id için bu warningi çıkardir
@Entity
@Table(name = "drugs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drug_name", nullable = false, length = 100)
    private String drugName;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 500)
    private String instruction;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    @JsonIgnore
    private Receipt receipt;;
}
