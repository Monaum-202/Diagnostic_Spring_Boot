package com.example.diagnostic_test.dto;

import com.example.diagnostic_test.entity.Doctors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;  // Department's unique identifier

    private String name;  // Name of the department

    private String description;  // Description of the department

    private List<Doctors> doctorNames;  // List of doctors' names in the department

    private List<Doctors> appointmentDates;  // List

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Doctors> getDoctorNames() {
        return doctorNames;
    }

    public void setDoctorNames(List<Doctors> doctorNames) {
        this.doctorNames = doctorNames;
    }

    public List<Doctors> getAppointmentDates() {
        return appointmentDates;
    }

    public void setAppointmentDates(List<Doctors> appointmentDates) {
        this.appointmentDates = appointmentDates;
    }


    // of appointment dates associated with the department
}
