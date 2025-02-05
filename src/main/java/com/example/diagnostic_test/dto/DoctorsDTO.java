package com.example.diagnostic_test.dto;

import com.example.diagnostic_test.entity.security.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorsDTO {

    private Long id;  // Unique identifier for each doctor

    private String name;  // Doctor's name

    private String gender;  // Doctor's gender

    private String departmentName;  // Name of the department the doctor belongs to
    private Long departmentId; // ID of the associated department

    private String contactNumber;  // Doctor's contact number

    private String email;  // Doctor's email

    private String qualification;  // Doctor's educational qualification

    private String specialty;  // Doctor's specialty

    private String scheduleTime;  // Doctor's available schedule time

    private Users userName;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public Users getUserName() {
        return userName;
    }

    public void setUserName(Users userName) {
        this.userName = userName;
    }
}
