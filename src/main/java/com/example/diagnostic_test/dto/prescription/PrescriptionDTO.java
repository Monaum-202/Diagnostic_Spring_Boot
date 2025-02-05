package com.example.diagnostic_test.dto.prescription;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {

    private String name;
    private String phone;
    private int age;
    private String sex;
    private String advice;
    private LocalDate followup;

    private List<PMedicineDTO> medicines;
    private List<InvestigationDTO> investigations;
    private List<DiagnosisDTO> diagnosis;
    private List<ComplaintDTO> complaints;


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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public LocalDate getFollowup() {
        return followup;
    }

    public void setFollowup(LocalDate followup) {
        this.followup = followup;
    }

    public List<PMedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PMedicineDTO> medicines) {
        this.medicines = medicines;
    }

    public List<InvestigationDTO> getInvestigations() {
        return investigations;
    }

    public void setInvestigations(List<InvestigationDTO> investigations) {
        this.investigations = investigations;
    }

    public List<DiagnosisDTO> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<DiagnosisDTO> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<ComplaintDTO> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ComplaintDTO> complaints) {
        this.complaints = complaints;
    }
}
