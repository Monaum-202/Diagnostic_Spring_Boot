package com.example.diagnostic_test.dto.diagnosticReceipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiagnosticMoneyReciptDTO2 {

    private Long id;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private Double payableAmount;
    private Double paidAmount;

    public DiagnosticMoneyReciptDTO2(Long id, LocalDateTime createdAt, Double totalAmount, Double payableAmount, Double paidAmount) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.payableAmount = payableAmount;
        this.paidAmount = paidAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }
}
