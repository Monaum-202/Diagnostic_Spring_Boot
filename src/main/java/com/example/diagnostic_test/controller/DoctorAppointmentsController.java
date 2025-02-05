package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.dto.DoctorAppointmentsDTO;
import com.example.diagnostic_test.entity.Department;
import com.example.diagnostic_test.entity.DoctorAppointments;
import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.repository.DepartmentRepository;
import com.example.diagnostic_test.repository.DoctorAppointmentsRepository;
import com.example.diagnostic_test.repository.DoctorsRepository;
import com.example.diagnostic_test.service.DoctorAppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/doctorAppointments")
public class DoctorAppointmentsController {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorsRepository doctorRepository;

    @Autowired
    private DoctorAppointmentsRepository doctorAppointmentsRepository;

    @Autowired
    private DoctorAppointmentService doctorAppointmentService;

    // GET all DoctorAppointments
    @GetMapping
    public List<DoctorAppointments> getAllAppointments() {
        return doctorAppointmentService.getAllAppointments();
    }
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

//
//    @GetMapping("/patients/{doctorId}")
//    public List<DoctorAppointments> getPatientsByDoctor(@PathVariable Long doctorId) {
//        List<DoctorAppointments> patientList = doctorAppointmentsRepository.findAllPatientByDoctorId(doctorId);
//        List<DoctorAppointments> newPatientList = new ArrayList<>();
//
//        for (DoctorAppointments dc: patientList
//        ) {
//            dc.setDepartment(null);
//            newPatientList.add(dc);
//        }
////        return departmentRepository.getById(departmentId).getDoctors();
//        return newPatientList;
//    }

    @GetMapping("/patients/{username}")
    public List<DoctorAppointments> getPatientsByDoctor(@PathVariable("username") String username) {
        System.out.println(username);
        List<DoctorAppointments> patientList = doctorAppointmentsRepository.findAllPatientByDoctorUserName(username);
        List<DoctorAppointments> newPatientList = new ArrayList<>();

        for (DoctorAppointments dc: patientList
        ) {
            dc.setDepartment(null);
            newPatientList.add(dc);
        }
//        return departmentRepository.getById(departmentId).getDoctors();
        return newPatientList;
    }


    // Get doctors by department
    @GetMapping("/doctors/by-department/{departmentId}")
    public List<Doctors> getDoctorsByDepartment(@PathVariable Long departmentId) {
        List<Doctors> dcList = doctorRepository.findAllByDepartmentId(departmentId);
        List<Doctors> newDcList = new ArrayList<>();

        for (Doctors dc: dcList
             ) {
            dc.setDepartment(null);
            newDcList.add(dc);
        }
//        return departmentRepository.getById(departmentId).getDoctors();
        return newDcList;
    }


    @PostMapping
    public ResponseEntity<String> createAppointment(
            @Valid @RequestBody DoctorAppointmentsDTO doctorAppointmentsDTO,
            BindingResult bindingResult) {

        // Validate the DTO
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Validation failed: " + bindingResult.getAllErrors());
        }

        try {
            // Create the appointment using the service
            DoctorAppointments appointment = doctorAppointmentService.createAppointment(doctorAppointmentsDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Appointment created successfully with ID: " + appointment.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }




    // GET DoctorAppointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorAppointments> getAppointmentById(@PathVariable Long id) {
        DoctorAppointments appointment = doctorAppointmentService.getAppointmentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        return ResponseEntity.ok(appointment);
    }

    // CREATE or UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<DoctorAppointments> updateAppointment(
            @PathVariable Long id,
            @RequestBody DoctorAppointmentsDTO appointmentDTO) {
        DoctorAppointments updatedAppointment = doctorAppointmentService.updateAppointment(id, appointmentDTO);
        return ResponseEntity.ok(updatedAppointment);
    }

    // DELETE DoctorAppointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        doctorAppointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<Page<DoctorAppointments>> searchAppointments(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String contactNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        Page<DoctorAppointments> appointments = doctorAppointmentService.searchAppointments(patientName, contactNumber, email, appointmentDate, pageable);
        return ResponseEntity.ok(appointments);
    }

}




