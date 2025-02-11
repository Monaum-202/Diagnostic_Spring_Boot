package com.example.diagnostic_test.repository.prescriptionRepo;

import com.example.diagnostic_test.entity.Prescription.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

        @Query(value = """
        SELECT 
            p.id,
            p.name,
            p.phone,
            p.sex,
            p.age,
            p.created_at,
            p.advice,
            p.followup,
            pinv.investigation,
            pd.diagnosis,
            pc.complaints,
            pm.medicine,
            pm.dosage,
            pm.frequency,
            pm.duration
        FROM prescriptions p
        LEFT JOIN prescription_investigations pinv ON p.id = pinv.prescription_id
        LEFT JOIN prescription_diagnosis pd ON p.id = pd.prescription_id
        LEFT JOIN prescription_complaints pc ON p.id = pc.prescription_id
        LEFT JOIN prescription_medicine pm ON p.id = pm.prescription_id
        WHERE p.id = :id
        """, nativeQuery = true)
        List<Object[]> findPrescriptionById(@Param("id") Long id);
    }




//    Page<Prescription> findByDoctorsId(Long doctorId, Pageable pageable);
//
//    Page<Prescription> findByPatientNameContaining(String patientName, Pageable pageable);
//
//    Page<Prescription> findByDoctorsIdAndPatientNameContaining(Long doctorId, String patientName, Pageable pageable);
//
//    Page<Prescription> findAll(Pageable pageable);