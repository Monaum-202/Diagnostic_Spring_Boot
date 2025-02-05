package com.example.diagnostic_test.repository.medicineRepo;

import com.example.diagnostic_test.entity.Medicine.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<MedicineCategory,Long> {
}
