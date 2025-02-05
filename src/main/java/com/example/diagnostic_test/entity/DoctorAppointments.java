package com.example.diagnostic_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class DoctorAppointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Appointment ID

    private String patientName;  // Patient's name

    private String contactNumber;  // Patient's contact number

    private String email;  // Patient's email

    private String address;

    private LocalDate appointmentDate;  // Appointment date


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt; // Appointment time

    @Column(columnDefinition = "TEXT")
    private String message;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnore
    private Doctors doctors;

    @Transient
    private String doctorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department department;


    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = LocalDateTime.now();
    }


    public String getDoctorName() {
        return doctors.getName();
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetientName() {
        return patientName;
    }

    public void setPetientName(String petientName) {
        this.patientName = petientName;
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



    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

//    public LocalTime getAppointmentTime() {
//        return createdAt;
//    }

//    public void setAppointmentTime(LocalTime createdAt) {
//        this.createdAt = createdAt;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



    @Override
    public String toString() {
        return "DoctorAppointments{" +
                "id=" + id +
                ", petientName='" + patientName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", appointmentDate=" + appointmentDate +
//                ", createdAt=" + createdAt +
                ", message='" + message + '\'' +
                ", doctors=" + doctors +
                ", department=" + department +
                "}\n";
    }
}