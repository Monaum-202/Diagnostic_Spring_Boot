package com.example.diagnostic_test.dto.diagnosticReceipt;

public class DiagnosticMoneyReciptDTO3 {

    private Long id;
    private String patientName;
    private String mobile;
    private Integer age;
    private String sex;
    private String createdAt;
    private Double totalAmount;
    private Double discount;
    private Double payableAmount;
    private Double paidAmount;
    private Double dueAmount;
    private String testName;
    private Double testPrice;

    // Constructors
    public DiagnosticMoneyReciptDTO3() {
    }

    public DiagnosticMoneyReciptDTO3(Long id, String patientName, String mobile, Integer age, String sex,
                                     String createdAt, Double totalAmount, Double discount,
                                     Double payableAmount, Double paidAmount, Double dueAmount,
                                     String testName, Double testPrice) {
        this.id = id;
        this.patientName = patientName;
        this.mobile = mobile;
        this.age = age;
        this.sex = sex;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.payableAmount = payableAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.testName = testName;
        this.testPrice = testPrice;
    }

    // Getters and Setters
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Double getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(Double testPrice) {
        this.testPrice = testPrice;
    }
}
