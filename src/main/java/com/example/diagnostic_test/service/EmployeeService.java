package com.example.diagnostic_test.service;

import com.example.diagnostic_test.entity.Employee;
import com.example.diagnostic_test.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhone(updatedEmployee.getPhone());
            employee.setRole(updatedEmployee.getRole());
            employee.setDateOfJoining(updatedEmployee.getDateOfJoining());
            employee.setSalary(updatedEmployee.getSalary());
            employee.setManagerId(updatedEmployee.getManagerId());
            employee.setActive(updatedEmployee.isActive());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Delete an employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Search employees with pagination
    public Page<Employee> searchEmployees(String firstName, String lastName, String email, String role, Pageable pageable) {
        return employeeRepository.searchEmployees(firstName, lastName, email, role, pageable);
    }
}
