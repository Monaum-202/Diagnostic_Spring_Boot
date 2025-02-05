package com.example.diagnostic_test.repository;

import com.example.diagnostic_test.entity.diagonesticEntry.DiagnosticMoneyReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticMoneyReceiptRepository extends JpaRepository<DiagnosticMoneyReceipt,Long> {

    @Query("SELECT d FROM DiagnosticMoneyReceipt d " +
            "WHERE (:patientName IS NULL OR LOWER(d.patientName) LIKE LOWER(CONCAT('%', :patientName, '%'))) " +
            "AND (:mobile IS NULL OR d.mobile LIKE CONCAT('%', :mobile, '%')) " +
            "AND (:refById IS NULL OR d.refBy.id = :refById)")
    Page<DiagnosticMoneyReceipt> searchDiagnosticMoneyReceipts(
            @Param("patientName") String patientName,
            @Param("mobile") String mobile,
            @Param("refById") Long refById,
            Pageable pageable
    );
}
