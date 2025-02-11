package com.example.diagnostic_test.repository;

import com.example.diagnostic_test.dto.diagnosticReceipt.DiagnosticMoneyReciptDTO3;
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

//    @Query("SELECT DiagnosticMoneyReciptDTO3(d.id, d.patientName, d.mobile, d.age, d.sex, d.createdAt, d.totalAmount, d.discount, d.payableAmount, d.paidAmount, d.dueAmount, dtt.name, dtt.price) " +
//            "FROM DiagnosticMoneyReceipt d " +
//            "LEFT JOIN DiagnoTests dt ON d.id = dt.diagnosticMoneyReceipt.id " +
//            "LEFT JOIN DiagonesticTest dtt ON dt.diagonesticTest.id = dtt.id " +
//            "WHERE d.id = :id")
//    List<DiagnosticMoneyReciptDTO3> getDiagnosticMoneyReceiptById(@Param("id") Long id);


    @Query(value = "SELECT d.id, d.patient_name, d.mobile, d.age, d.sex, d.created_at, d.total_amount, d.discount, d.payable_amount, d.paid_amount, d.due_amount, dtt.name, dtt.price " +
            "FROM diagnostic_money_receipt d " +
            "LEFT JOIN diagno_tests dt ON d.id = dt.diagnostic_money_receipt_id " +
            "LEFT JOIN diagonestic_test dtt ON dt.diagonestic_test_id = dtt.id " +
            "WHERE d.id = :id", nativeQuery = true)
    List<Object[]> findByReceiptId(@Param("id") Long id);


}
