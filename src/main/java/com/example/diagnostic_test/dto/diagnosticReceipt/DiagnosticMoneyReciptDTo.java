package com.example.diagnostic_test.dto.diagnosticReceipt;

import com.example.diagnostic_test.dto.DiagonesticTestDTO;
import com.example.diagnostic_test.dto.DoctorsDTO;

import java.util.List;

public class DiagnosticMoneyReciptDTo {

    private Long id;
    private String patientName;
    private String age;
    private String sex;
    private String mobile;

    private double totalAmount;
    private double discount;
    private double payableAmount;
    private double paidAmount;
    private double dueAmount;

    private Long refBy;
    private List<DiagonesticTestDTO> diagonesticTests;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRefBy() {
        return refBy;
    }

    public void setRefBy(Long refBy) {
        this.refBy = refBy;
    }

    public List<DiagonesticTestDTO> getDiagonesticTests() {
        return diagonesticTests;
    }

    public void setDiagonesticTests(List<DiagonesticTestDTO> diagonesticTests) {
        this.diagonesticTests = diagonesticTests;
    }
}
