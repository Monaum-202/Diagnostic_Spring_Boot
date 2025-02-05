package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.dto.DepartmentDTO;
import com.example.diagnostic_test.entity.Department;
import com.example.diagnostic_test.repository.DepartmentRepository;
import com.example.diagnostic_test.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;



    @GetMapping
    public List<Department> getAllUsers() {
        return departmentRepository.findAll();
    }

//    public Page<DepartmentDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
//        Sort sort = Sort.by(Sort.Order.asc(sortBy)); // Default is ascending order
//        if ("desc".equalsIgnoreCase(sortDir)) {
//            sort = Sort.by(Sort.Order.desc(sortBy));
//        }
//
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        Page<DepartmentDTO> userDTOPage = departmentRepository.findAll(pageable).map(user -> mapToDTO(user, new UserDTO()));
//        return userDTOPage;
//    }
    @GetMapping("/{id}")
    public Department getUserById(@PathVariable long id) {
        Optional<Department> user = departmentRepository.findById(id);
        return user.orElse(null);  // Return the user if found, otherwise return null
    }

    @PostMapping
    public Department createUser(@RequestBody Department department) {
        return departmentRepository.save(department);  // Save the user to the database
    }

    @PutMapping("/{id}")
    public Department updateUser(@PathVariable long id, @RequestBody Department department) {
        if (departmentRepository.existsById(id)) {
            department.setId(id);
            return departmentRepository.save(department);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return "User deleted successfully.";
        }
        return "User not found.";
    }



//    http://localhost:9090/api/department/search?name=Dental&page=0&size=5
    @GetMapping("/search")
    public Page<Department> searchDepartments(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentService.searchDepartments(name, description, pageable);
    }
}
