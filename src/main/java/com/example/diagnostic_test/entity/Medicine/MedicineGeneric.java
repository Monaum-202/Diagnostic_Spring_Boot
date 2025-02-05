package com.example.diagnostic_test.entity.Medicine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
public class MedicineGeneric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genericId;
    private String genericName;
    private String precaution;
    private String indication;
    private String contraIndication;
    private String dose;
    private String sideEffect;
    private String pregnancyCategoryId;
    private String modeOfAction;
    private String interaction;

    @OneToMany(mappedBy = "generic", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Medicine> medicines;
}
