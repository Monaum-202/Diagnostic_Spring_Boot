package com.example.diagnostic_test.dto;

import com.example.diagnostic_test.entity.Department;
import com.example.diagnostic_test.entity.Doctors;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DoctorAppointmentsDTO {
    @NotBlank
    private String patientName;

    @NotBlank
    private String contactNumber;

    @Email
    private String email;

    private String address;
    @NotNull
    private LocalDate appointmentDate;

    private String message;

    @NotNull
    private Long doctors;

    @NotNull
    private Long department;



    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDoctors() {
        return doctors;
    }

    public void setDoctors(Long doctors) {
        this.doctors = doctors;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }
}