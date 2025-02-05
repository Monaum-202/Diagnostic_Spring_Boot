package com.example.diagnostic_test.entity;


import com.example.diagnostic_test.dto.DiagonesticTestDTO;
import com.example.diagnostic_test.entity.Prescription.Prescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class DiagonesticTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

//    @ManyToOne
//    @JsonIgnore
//    private Prescription prescription;

    // Convert entity to DTO
    public DiagonesticTestDTO mapToDTO() {
        DiagonesticTestDTO dto = new DiagonesticTestDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setPrice(this.price);
        return dto;
    }

    // Convert DTO to entity
    public static DiagonesticTest mapToEntity(DiagonesticTestDTO dto) {
        DiagonesticTest diagonesticTest = new DiagonesticTest();
        diagonesticTest.setId(dto.getId());
        diagonesticTest.setName(dto.getName());
        diagonesticTest.setPrice(dto.getPrice());
        return diagonesticTest;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}