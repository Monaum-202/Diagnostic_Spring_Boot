package com.example.diagnostic_test.entity;

import com.example.diagnostic_test.dto.DoctorsDTO;
import com.example.diagnostic_test.entity.Prescription.Prescription;
import com.example.diagnostic_test.entity.diagonesticEntry.DiagnosticMoneyReceipt;
import com.example.diagnostic_test.entity.security.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each doctor

    private String name; // Doctor's first name

    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Department department; // Reference to the department

    private String contactNumber; // Doctor's contact number

    private String email; // Doctor's email

    private String qualification; // Educational qualifications

    private String specialty;

    private String scheduleTime;

    @OneToOne
    @JoinColumn(name = "user_name", referencedColumnName = "userName", unique = true)
    private Users user;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<DoctorAppointments> doctorAppointments;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Prescription> prescriptions;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<DiagnosticMoneyReceipt> diagnosticMoneyReceipts;

    // Mapping to DTO
    public DoctorsDTO mapToDTO() {
        DoctorsDTO dto = new DoctorsDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setGender(this.gender);
        dto.setDepartmentId(this.department != null ? this.department.getId() : null);
        dto.setDepartmentName(this.department != null ? this.department.getName() : null);
        dto.setContactNumber(this.contactNumber);
        dto.setEmail(this.email);
        dto.setQualification(this.qualification);
        dto.setSpecialty(this.specialty);
        dto.setScheduleTime(this.scheduleTime);


        return dto;
    }

    // Mapping from DTO to Entity
    public static Doctors mapToEntity(DoctorsDTO dto, Department department, Users user) {
        Doctors doctor = new Doctors();
        doctor.setId(dto.getId());
        doctor.setName(dto.getName());
        doctor.setGender(dto.getGender());
        doctor.setDepartment(department); // Map the department using the provided object
        doctor.setContactNumber(dto.getContactNumber());
        doctor.setEmail(dto.getEmail());
        doctor.setQualification(dto.getQualification());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setScheduleTime(dto.getScheduleTime());
        doctor.setUsers(dto.getUserName()); // Map the user from the provided object (assumes user is passed)

        return doctor;
    }

    // Getters and setters
    // You can generate getters and setters using your IDE or manually



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public List<DoctorAppointments> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<DoctorAppointments> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<DiagnosticMoneyReceipt> getDiagnosticMoneyReceipts() {
        return diagnosticMoneyReceipts;
    }

    public void setDiagnosticMoneyReceipts(List<DiagnosticMoneyReceipt> diagnosticMoneyReceipts) {
        this.diagnosticMoneyReceipts = diagnosticMoneyReceipts;
    }


    public Users getUsers() {
        return user;
    }

    public void setUsers(Users users) {
        this.user = users;
    }
}

