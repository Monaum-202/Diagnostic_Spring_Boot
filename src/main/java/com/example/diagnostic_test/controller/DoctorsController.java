package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.entity.Department;
import com.example.diagnostic_test.entity.DoctorAppointments;
import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.repository.DoctorAppointmentsRepository;
import com.example.diagnostic_test.repository.DoctorsRepository;
import com.example.diagnostic_test.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/doctor")
public class DoctorsController {


    @Autowired
    private DoctorsRepository doctorRepository;

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private DoctorAppointmentsRepository doctorAppointmentsRepository;

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

    @GetMapping
    public List<Doctors> getAllUsers() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public Doctors createUser(@RequestBody Doctors doctors) {
        return doctorRepository.save(doctors);  // Save the user to the database
    }

    @GetMapping("/{id}")
    public Doctors getUserById(@PathVariable long id) {
        Optional<Doctors> user = doctorRepository.findById(id);
        return user.orElse(null);  // Return the user if found, otherwise return null
    }


    @PutMapping("/{id}")
    public Doctors updateUser(@PathVariable long id, @RequestBody Doctors doctors) {
        if (doctorRepository.existsById(id)) {
            doctors.setId(id);
            return doctorRepository.save(doctors);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return "User deleted successfully.";
        }
        return "User not found.";
    }


    // Search API for doctors with pagination
    @GetMapping("/search")
    public Page<Doctors> searchDoctors(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "specialty", required = false) String specialty,
            @RequestParam(value = "scheduleTime", required = false) String scheduleTime,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return doctorsService.searchDoctors(name, gender, specialty, scheduleTime, pageable);
    }



//    @GetMapping("/department/{departmentId}")
//    public Page<Doctors> findDoctorsByDepartment(
//            @PathVariable Long departmentId,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        return doctorsService.findDoctorsByDepartment(departmentId, pageable);
//    }


}


