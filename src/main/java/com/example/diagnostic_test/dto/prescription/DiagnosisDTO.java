package com.example.diagnostic_test.dto.prescription;

public class DiagnosisDTO {
    private long id;
    private String diagnosis;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
