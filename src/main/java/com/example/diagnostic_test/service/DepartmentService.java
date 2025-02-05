package com.example.diagnostic_test.service;

import com.example.diagnostic_test.entity.Department;
import com.example.diagnostic_test.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<Department> searchDepartments(String name, String description, Pageable pageable) {
        return departmentRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(name, description, pageable);
    }
}
