package com.example.diagnostic_test.service;


import com.example.diagnostic_test.dto.prescription.PrescriptionDTO;
import com.example.diagnostic_test.dto.prescription.PrescriptionDTO2;
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

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

    public List<PrescriptionDTO2> getPrescriptionDetails(Long id) {
        // Fetch data from repository
        List<Object[]> results = prescriptionRepository.findPrescriptionById(id);

        // Convert to DTO list
        List<PrescriptionDTO2> prescriptionDTOs = new ArrayList<>();
        for (Object[] row : results) {
            PrescriptionDTO2 prescriptionDTO = new PrescriptionDTO2(
                    parseLong(row[0]),           // id
                    (String) row[1],            // name
                    (String) row[2],            // phone
                    (String) row[3],            // sex
                    parseInteger(row[4]),       // age
                    formatDate(row[5]),         // createdAt (String format)
                    (String) row[6],            // advice
                    formatDate(row[7]),     // followup (LocalDate)
                    (String) row[8],            // investigation
                    (String) row[9],            // diagnosis
                    (String) row[10],           // complaints
                    (String) row[11],           // medicine
                    (String) row[12],           // dosage
                    (String) row[13],           // frequency
                    (String) row[14]            // duration
            );
            prescriptionDTOs.add(prescriptionDTO);
        }
        return prescriptionDTOs;
    }
    private Long parseLong(Object obj) {
        return (obj != null) ? ((Number) obj).longValue() : null;
    }

    private Integer parseInteger(Object obj) {
        return (obj != null) ? ((Number) obj).intValue() : null;
    }

    private String safeCast(Object obj) {
        return (obj != null) ? obj.toString() : null;
    }

    private String formatDate(Object obj) {
        if (obj instanceof java.sql.Timestamp) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((java.sql.Timestamp) obj);
        }
        return null;
    }

    private LocalDate parseLocalDate(Object obj) {
        if (obj instanceof java.sql.Date) {
            return ((java.sql.Date) obj).toLocalDate();
        }
        return null;
    }

//    // Helper Methods to Parse Values Safely
//    private Long parseLong(Object obj) {
//        if (obj instanceof Number) {
//            return ((Number) obj).longValue();
//        } else if (obj instanceof String) {
//            return Long.parseLong((String) obj);
//        }
//        return null;
//    }
//
//    private Integer parseInteger(Object obj) {
//        if (obj instanceof Number) {
//            return ((Number) obj).intValue();
//        } else if (obj instanceof String) {
//            return Integer.parseInt((String) obj);
//        }
//        return null;
//    }
//
//    // Convert java.sql.Date or Timestamp to String (formatted)
//    private String formatDate(Object obj) {
//        if (obj instanceof Date) {
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) obj);
//        } else if (obj instanceof Timestamp) {
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) obj);
//        }
//        return obj != null ? obj.toString() : null;
//    }
//
//    // Convert java.sql.Timestamp or java.sql.Date to LocalDateTime
//    private LocalDateTime parseLocalDateTime(Object obj) {
//        if (obj instanceof Timestamp) {
//            return ((Timestamp) obj).toLocalDateTime();
//        } else if (obj instanceof Date) {
//            return ((Date) obj).toLocalDate().atStartOfDay();
//        } else if (obj instanceof String) {
//            return LocalDateTime.parse((String) obj, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        }
//        return null;
//    }
//
//    // Convert java.sql.Date or String to LocalDate
//    private LocalDate parseLocalDate(Object obj) {
//        if (obj instanceof Date) {
//            return ((Date) obj).toLocalDate();
//        } else if (obj instanceof String) {
//            return LocalDate.parse((String) obj, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        }
//        return null;
//    }
}
