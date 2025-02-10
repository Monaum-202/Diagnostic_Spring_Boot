package com.example.diagnostic_test.entity.doctor;

import com.example.diagnostic_test.entity.Doctors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@ToString
public class DoctorNote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each doctor

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Doctors doctor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctors(Doctors doctor) {
        this.doctor = doctor;
    }
}
