package com.example.diagnostic_test.repository.prescriptionRepo;

import com.example.diagnostic_test.entity.Prescription.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {


}
//    Page<Prescription> findByDoctorsId(Long doctorId, Pageable pageable);
//
//    Page<Prescription> findByPatientNameContaining(String patientName, Pageable pageable);
//
//    Page<Prescription> findByDoctorsIdAndPatientNameContaining(Long doctorId, String patientName, Pageable pageable);
//
//    Page<Prescription> findAll(Pageable pageable);