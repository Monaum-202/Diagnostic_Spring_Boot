package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.entity.DiagonesticTest;
import com.example.diagnostic_test.repository.DiagonesticTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/diagnostic")
public class DiagnosticTestController {

    @Autowired
    private DiagonesticTestRepository diagonesticTestRepository;
    @GetMapping
    public List<DiagonesticTest> getAllMedicines() {
        return diagonesticTestRepository.findAll();
    }

    @PostMapping
    public DiagonesticTest createUser(@RequestBody DiagonesticTest diagonesticTest) {
        return diagonesticTestRepository.save(diagonesticTest);  // Save the user to the database
    }

    @GetMapping("/{id}")
    public DiagonesticTest getUserById(@PathVariable long id) {
        Optional<DiagonesticTest> user = diagonesticTestRepository.findById(id);
        return user.orElse(null);  // Return the user if found, otherwise return null
    }


    @PutMapping("/{id}")
    public DiagonesticTest updateUser(@PathVariable long id, @RequestBody DiagonesticTest diagonesticTest) {
        if (diagonesticTestRepository.existsById(id)) {
            diagonesticTest.setId(id);
            return diagonesticTestRepository.save(diagonesticTest);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        if (diagonesticTestRepository.existsById(id)) {
            diagonesticTestRepository.deleteById(id);
            return "User deleted successfully.";
        }
        return "User not found.";
    }
}
