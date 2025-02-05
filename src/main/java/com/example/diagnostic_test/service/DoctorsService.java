package com.example.diagnostic_test.service;

import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;
    // Search method with pagination
    public Page<Doctors> searchDoctors(String name, String gender, String specialty, String scheduleTime, Pageable pageable) {
        return doctorsRepository.findByNameContainingIgnoreCaseOrGenderContainingIgnoreCaseOrSpecialtyContainingIgnoreCaseOrScheduleTimeContainingIgnoreCase(
                name, gender, specialty, scheduleTime, pageable);
    }

    // Optional: Search by department with pagination
    public Page<Doctors> findDoctorsByDepartment(Long departmentId, Pageable pageable) {
        return doctorsRepository.findByDepartmentId(departmentId, pageable);
    }
}
