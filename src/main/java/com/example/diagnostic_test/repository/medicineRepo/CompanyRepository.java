package com.example.diagnostic_test.repository.medicineRepo;

import com.example.diagnostic_test.entity.Medicine.MedicineCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<MedicineCompany, Long> {}
