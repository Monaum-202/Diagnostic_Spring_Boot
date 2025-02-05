package com.example.diagnostic_test.service;

import com.example.diagnostic_test.dto.DoctorAppointmentsDTO;
import com.example.diagnostic_test.entity.Department;
import com.example.diagnostic_test.entity.DoctorAppointments;
import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.repository.DepartmentRepository;
import com.example.diagnostic_test.repository.DoctorAppointmentsRepository;
import com.example.diagnostic_test.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DoctorAppointmentService {

    @Autowired
    private DoctorAppointmentsRepository doctorAppointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public DoctorAppointments createAppointment(DoctorAppointmentsDTO appointmentDTO) {
        // Convert DTO to Entity (if necessary)
        DoctorAppointments appointment = new DoctorAppointments();
        appointment.setPatientName(appointmentDTO.getPatientName());
        appointment.setContactNumber(appointmentDTO.getContactNumber());
        appointment.setEmail(appointmentDTO.getEmail());
        appointment.setAddress(appointmentDTO.getAddress());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setMessage(appointmentDTO.getMessage());


//        Department department = Department.mapToEntity(appointmentDTO.getDepartment());
//        Doctors doctors = Doctors.mapToEntity(appointmentDTO.getDoctors(), department);
        Department department = departmentRepository.findById(appointmentDTO.getDepartment())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Doctors doctors = doctorsRepository.findById(appointmentDTO.getDoctors())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setDoctors(doctors);
        appointment.setDepartment(department);

        // Save appointment to the database
        return doctorAppointmentsRepository.save(appointment);
    }

    // Get all DoctorAppointments
    public List<DoctorAppointments> getAllAppointments() {
        return doctorAppointmentsRepository.findAll();
    }

    // Get DoctorAppointment by ID
    public Optional<DoctorAppointments> getAppointmentById(Long id) {
        return doctorAppointmentsRepository.findById(id);
    }

    // Update DoctorAppointment (or create if not exists)
    public DoctorAppointments updateAppointment(Long id, DoctorAppointmentsDTO appointmentDTO) {
        DoctorAppointments existingAppointment = doctorAppointmentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Department department = departmentRepository.findById(appointmentDTO.getDepartment())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Doctors doctors = doctorsRepository.findById(appointmentDTO.getDoctors())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existingAppointment.setPatientName(appointmentDTO.getPatientName());
        existingAppointment.setContactNumber(appointmentDTO.getContactNumber());
        existingAppointment.setEmail(appointmentDTO.getEmail());
        existingAppointment.setAddress(appointmentDTO.getAddress());
        existingAppointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        existingAppointment.setMessage(appointmentDTO.getMessage());
        existingAppointment.setDepartment(department);
        existingAppointment.setDoctors(doctors);

        return doctorAppointmentsRepository.save(existingAppointment);
    }

    // Delete DoctorAppointment by ID
    public void deleteAppointment(Long id) {
        if (!doctorAppointmentsRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found");
        }
        doctorAppointmentsRepository.deleteById(id);
    }


    public Page<DoctorAppointments> searchAppointments(
            String patientName,
            String contactNumber,
            String email,
            LocalDate appointmentDate,
            Pageable pageable) {
        return doctorAppointmentsRepository.searchAppointments(patientName, contactNumber, email, appointmentDate, pageable);
    }
}

