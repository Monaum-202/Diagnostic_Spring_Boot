package com.example.diagnostic_test.dto.prescription;

public class InvestigationDTO {
    private long id;
    private String investigations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvestigations() {
        return investigations;
    }

    public void setInvestigations(String investigations) {
        this.investigations = investigations;
    }
}
