package com.example.diagnostic_test.dto.prescription;

import java.time.LocalDate;

public class PrescriptionDTO2 {

    private Long id;
    private String name;
    private String phone;
    private String sex;
    private Integer age;
    private String createdAt;
    private String advice;
    private String followup;
    private String investigation;
    private String diagnosis;
    private String complaints;
    private String medicine;
    private String dosage;
    private String frequency;
    private String duration;

    public PrescriptionDTO2() {
    }

    public PrescriptionDTO2(Long id, String name, String phone, String sex, Integer age, String createdAt,
                            String advice, String followup, String investigation, String diagnosis,
                            String complaints, String medicine, String dosage, String frequency, String duration) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.createdAt = createdAt;
        this.advice = advice;
        this.followup = followup;
        this.investigation = investigation;
        this.diagnosis = diagnosis;
        this.complaints = complaints;
        this.medicine = medicine;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
