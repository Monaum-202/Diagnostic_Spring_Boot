package com.example.diagnostic_test.entity.diagonesticEntry;

import com.example.diagnostic_test.dto.DoctorsDTO;
import com.example.diagnostic_test.dto.diagnosticReceipt.DiagnosticMoneyReciptDTo;
import com.example.diagnostic_test.entity.Doctors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DiagnosticMoneyReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Doctors refBy;

    private String patientName;
    private String age;
    private String sex;
    private String mobile;
    private LocalDateTime createdAt;


    private double totalAmount;
    private double discount;
    private double payableAmount;
    private double paidAmount;
    private double dueAmount;



    @OneToMany(mappedBy = "diagnosticMoneyReceipt", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DiagnoTests> diagonesticTests;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctors getRefBy() {
        return refBy;
    }

    public void setRefBy(Doctors refBy) {
        this.refBy = refBy;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public List<DiagnoTests> getDiagonesticTests() {
        return diagonesticTests;
    }

    public void setDiagonesticTests(List<DiagnoTests> diagonesticTests) {
        this.diagonesticTests = diagonesticTests;
    }



    // Method to map DTO to Entity
    public void mapFromDTO(DiagnosticMoneyReciptDTo dto, Doctors doctor, List<DiagnoTests> tests) {
        this.patientName = dto.getPatientName();
        this.age = dto.getAge();
        this.sex = dto.getSex();
        this.mobile = dto.getMobile();
        this.totalAmount = dto.getTotalAmount();
        this.discount = dto.getDiscount();
        this.payableAmount = dto.getPayableAmount();
        this.paidAmount = dto.getPaidAmount();
        this.dueAmount = dto.getDueAmount();
        this.refBy = doctor;
        this.diagonesticTests = tests;
    }

    // Method to map Entity to DTO
    public DiagnosticMoneyReciptDTo toDTO() {
        DiagnosticMoneyReciptDTo dto = new DiagnosticMoneyReciptDTo();
        dto.setId(this.id);
        dto.setPatientName(this.patientName);
        dto.setAge(this.age);
        dto.setSex(this.sex);
        dto.setMobile(this.mobile);
        dto.setTotalAmount(this.totalAmount);
        dto.setDiscount(this.discount);
        dto.setPayableAmount(this.payableAmount);
        dto.setPaidAmount(this.paidAmount);
        dto.setDueAmount(this.dueAmount);

        return dto;
    }
}
