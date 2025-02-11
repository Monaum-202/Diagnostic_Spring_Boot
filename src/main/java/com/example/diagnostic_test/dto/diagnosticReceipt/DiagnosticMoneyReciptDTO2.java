package com.example.diagnostic_test.dto.diagnosticReceipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiagnosticMoneyReciptDTO2 {

    private Long id;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private Double payableAmount;
    private Double paidAmount;


    private String createdBy;

    private double discount;

    private double dueAmount;

    private Long refBy;

    public DiagnosticMoneyReciptDTO2(Long id, LocalDateTime createdAt, Double totalAmount, Double payableAmount, Double paidAmount, String createdBy, double discount, double dueAmount, Long refBy) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.payableAmount = payableAmount;
        this.paidAmount = paidAmount;
        this.createdBy = createdBy;
        this.discount = discount;
        this.dueAmount = dueAmount;
        this.refBy = refBy;
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


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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
}
