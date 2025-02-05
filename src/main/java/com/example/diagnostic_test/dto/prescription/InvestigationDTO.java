package com.example.diagnostic_test.dto.prescription;

public class InvestigationDTO {
    private long id;
    private String investigation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }
}
