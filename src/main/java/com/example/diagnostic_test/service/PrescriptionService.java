package com.example.diagnostic_test.service;


import com.example.diagnostic_test.dto.prescription.PrescriptionDTO;
import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.entity.Prescription.Prescription;
import com.example.diagnostic_test.entity.Prescription.PrescriptionMedicine;
import com.example.diagnostic_test.mapper.PrescriptionMapper;
import com.example.diagnostic_test.repository.DiagonesticTestRepository;
import com.example.diagnostic_test.repository.DoctorsRepository;
import com.example.diagnostic_test.repository.medicineRepo.MedicineRepository;
import com.example.diagnostic_test.repository.prescriptionRepo.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private DiagonesticTestRepository diagonesticTestRepository;


    public PrescriptionDTO savePrescription(PrescriptionDTO dto) {
        Prescription prescription = PrescriptionMapper.toEntity(dto);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return PrescriptionMapper.toDTO(savedPrescription);
    }

    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(PrescriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PrescriptionDTO getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .map(PrescriptionMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Prescription not found!"));
    }


    @Transactional
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        prescriptionRepository.delete(prescription);
    }

//    public Page<Prescription> searchPrescriptions(Long doctorId, String patientName, Pageable pageable) {
//        if (doctorId != null && patientName != null) {
//            return prescriptionRepository.findByDoctorsIdAndPatientNameContaining(doctorId, patientName, pageable);
//        } else if (doctorId != null) {
//            return prescriptionRepository.findByDoctorsId(doctorId, pageable);
//        } else if (patientName != null) {
//            return prescriptionRepository.findByPatientNameContaining(patientName, pageable);
//        } else {
//            return prescriptionRepository.findAll(pageable);
//        }
//    }

//    public Prescription createPrescription(PrescriptionDTO request) {
//        Prescription prescription = new Prescription();
//        Doctors doctor = doctorsRepository.findById(request.getDoctors())
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//        prescription.setDoctors(doctor);
//        prescription.setPatientName(request.getPatientName());
//        prescription.setCreatedAt(LocalDateTime.now());
//
//        List<PrescriptionMedicine> prescriptionMedicines = request.getMedicines().stream().map(med -> {
//            PrescriptionMedicine pm = new PrescriptionMedicine();
//            pm.setMedicine(medicineRepository.findById(med.getId()).orElseThrow());
//            pm.setDosage(med.getDosage());
//            pm.setFrequency(med.getFrequency());
//            pm.setPrescription(prescription);
//            return pm;
//        }).collect(Collectors.toList());
//
//        prescription.setMedicines(prescriptionMedicines);
//
//
//        List<PrescriptionTests> prescriptionTests = request.getDiagonesticTests().stream().map(dte -> {
//            PrescriptionTests pt = new PrescriptionTests();
//            pt.setDiagonesticTest(diagonesticTestRepository.findById(dte.getId()).orElseThrow());
//            pt.setPrescription(prescription);
//            return pt;
//        }).collect(Collectors.toList());
//
//        prescription.setDiagonesticTests(prescriptionTests);
//
//
//
//        return prescriptionRepository.save(prescription);
//    }



//    @Transactional
//    public Prescription updatePrescription(Long id, PrescriptionDTO request) {
//        Prescription prescription = prescriptionRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Prescription not found"));
//
//        Doctors doctor = doctorsRepository.findById(request.getDoctors())
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//        prescription.setDoctors(doctor);
//        prescription.setPatientName(request.getPatientName());
//
//        prescription.getMedicines().clear();
//        List<PrescriptionMedicine> updatedMedicines = request.getMedicines().stream().map(med -> {
//            PrescriptionMedicine pm = new PrescriptionMedicine();
//            pm.setMedicine(medicineRepository.findById(med.getId()).orElseThrow());
//            pm.setDosage(med.getDosage());
//            pm.setFrequency(med.getFrequency());
//            pm.setPrescription(prescription);
//            return pm;
//        }).collect(Collectors.toList());
//        prescription.setMedicines(updatedMedicines);
//
//        prescription.getDiagonesticTests().clear();
//        List<PrescriptionTests> updatedTests = request.getDiagonesticTests().stream().map(dte -> {
//            PrescriptionTests pt = new PrescriptionTests();
//            pt.setDiagonesticTest(diagonesticTestRepository.findById(dte.getId()).orElseThrow());
//            pt.setPrescription(prescription);
//            return pt;
//        }).collect(Collectors.toList());
//        prescription.setDiagonesticTests(updatedTests);
//
//        return prescriptionRepository.save(prescription);
//    }

//    public List<Prescription> getAllPrescriptions() {
//        return prescriptionRepository.findAll();
//    }
//
//    public Prescription getPrescriptionById(Long id) {
//        return prescriptionRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Prescription not found"));
//    }


}
