package com.example.diagnostic_test.repository.medicineRepo;

import com.example.diagnostic_test.entity.Medicine.MedicineGeneric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository extends JpaRepository<MedicineGeneric, Long> {}
