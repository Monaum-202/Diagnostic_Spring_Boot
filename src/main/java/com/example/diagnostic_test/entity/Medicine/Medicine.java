package com.example.diagnostic_test.entity.Medicine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "generic_id")
    @JsonIgnore
    private MedicineGeneric generic;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private MedicineCompany company;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private MedicineCategory category;
    private String medicineName;
    private String form;
    private String strength;
    private Double price;
    private String packSize;
//    private Double buyingPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicineGeneric getGeneric() {
        return generic;
    }

    public void setGeneric(MedicineGeneric generic) {
        this.generic = generic;
    }

    public MedicineCompany getCompany() {
        return company;
    }

    public void setCompany(MedicineCompany company) {
        this.company = company;
    }

    public MedicineCategory getCategory() {
        return category;
    }

    public void setCategory(MedicineCategory category) {
        this.category = category;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }
}
