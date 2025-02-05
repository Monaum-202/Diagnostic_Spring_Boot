package com.example.diagnostic_test.repository.medicineRepo;

import com.example.diagnostic_test.entity.Medicine.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query("SELECT m FROM Medicine m WHERE " +
            "(:medicineName IS NULL OR LOWER(m.medicineName) LIKE LOWER(CONCAT('%', :medicineName, '%'))) AND " +
            "(:form IS NULL OR LOWER(m.form) LIKE LOWER(CONCAT('%', :form, '%'))) AND " +
            "(:strength IS NULL OR LOWER(m.strength) LIKE LOWER(CONCAT('%', :strength, '%'))) AND " +
            "(:price IS NULL OR m.price = :price) AND " +
            "(:categoryId IS NULL OR m.category.id = :categoryId) AND " +
            "(:companyId IS NULL OR m.company.id = :companyId)")
    Page<Medicine> searchMedicines(
            @Param("medicineName") String medicineName,
            @Param("form") String form,
            @Param("strength") String strength,
            @Param("price") Double price,
            @Param("categoryId") Long categoryId,
            @Param("companyId") Long companyId,
            Pageable pageable
    );
}
