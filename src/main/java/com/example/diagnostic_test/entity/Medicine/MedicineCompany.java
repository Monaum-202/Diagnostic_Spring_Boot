package com.example.diagnostic_test.entity.Medicine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
public class MedicineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Medicine> medicines;


}
