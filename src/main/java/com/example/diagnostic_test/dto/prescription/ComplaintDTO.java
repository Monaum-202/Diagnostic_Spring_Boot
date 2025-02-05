package com.example.diagnostic_test.dto.prescription;

public class ComplaintDTO {
    private long id;
    private String complaints;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }
}
