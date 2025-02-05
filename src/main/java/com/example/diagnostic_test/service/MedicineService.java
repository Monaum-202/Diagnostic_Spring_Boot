package com.example.diagnostic_test.service;

import com.example.diagnostic_test.entity.Medicine.Medicine;
import com.example.diagnostic_test.repository.medicineRepo.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    // Search medicines with filters and pagination
    public Page<Medicine> searchMedicines(String medicineName, String form, String strength, Double price, Long categoryId, Long companyId, Pageable pageable) {
        return medicineRepository.searchMedicines(medicineName, form, strength, price, categoryId, companyId, pageable);
    }

}
