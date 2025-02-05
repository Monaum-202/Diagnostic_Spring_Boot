package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.entity.Medicine.Medicine;
import com.example.diagnostic_test.repository.medicineRepo.MedicineRepository;
import com.example.diagnostic_test.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/medicines")
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;


    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @PostMapping
    public Medicine createUser(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);  // Save the user to the database
    }

    @GetMapping("/{id}")
    public Medicine getUserById(@PathVariable long id) {
        Optional<Medicine> user = medicineRepository.findById(id);
        return user.orElse(null);  // Return the user if found, otherwise return null
    }


    @PutMapping("/{id}")
    public Medicine updateUser(@PathVariable long id, @RequestBody Medicine medicine) {
        if (medicineRepository.existsById(id)) {
            medicine.setId(id);
            return medicineRepository.save(medicine);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.deleteById(id);
            return "User deleted successfully.";
        }
        return "User not found.";
    }


    // Search medicines with filters and pagination
    @GetMapping("/search")
    public ResponseEntity<Page<Medicine>> searchMedicines(
            @RequestParam(required = false) String medicineName,
            @RequestParam(required = false) String form,
            @RequestParam(required = false) String strength,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long companyId,
            Pageable pageable // Automatically handles pagination parameters
    ) {
        Page<Medicine> medicines = medicineService.searchMedicines(medicineName, form, strength, price, categoryId, companyId, pageable);
        return ResponseEntity.ok(medicines);
    }

}
