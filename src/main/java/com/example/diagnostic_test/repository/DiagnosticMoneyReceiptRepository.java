package com.example.diagnostic_test.repository;

import com.example.diagnostic_test.entity.diagonesticEntry.DiagnosticMoneyReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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



    @Query(value = """
                SELECT dm.id, dm.created_at, dm.total_amount, dm.payable_amount, dm.paid_amount
                FROM diagnostic_money_receipt dm
                WHERE dm.created_at BETWEEN :startDate AND :endDate
            """, nativeQuery = true)
    List<Object[]> findReceiptsByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT * FROM diagnostic_money_receipt WHERE created_at BETWEEN :fromDate AND :toDate", nativeQuery = true)
    List<DiagnosticMoneyReceipt> findEventsByCreatedAtRange(LocalDateTime fromDate, LocalDateTime toDate);

    List<DiagnosticMoneyReceipt> findByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate);

}
