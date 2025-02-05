package com.example.diagnostic_test.mapper;

import com.example.diagnostic_test.dto.prescription.*;
import com.example.diagnostic_test.entity.Prescription.*;

import java.util.stream.Collectors;

public class PrescriptionMapper {
    public static Prescription toEntity(PrescriptionDTO dto) {
        Prescription prescription = new Prescription();
        prescription.setName(dto.getName());
        prescription.setPhone(dto.getPhone());
        prescription.setAge(dto.getAge());
        prescription.setSex(dto.getSex());
        prescription.setAdvice(dto.getAdvice());
        prescription.setFollowup(dto.getFollowup());

        prescription.setMedicines(dto.getMedicines().stream().map(m -> {
            PrescriptionMedicine medicine = new PrescriptionMedicine();
            medicine.setMedicine(m.getMedicine());
            medicine.setDosage(m.getDosage());
            medicine.setFrequency(m.getFrequency());
            medicine.setDuration(m.getDuration());
            medicine.setPrescription(prescription);
            return medicine;
        }).collect(Collectors.toList()));

        prescription.setInvestigations(dto.getInvestigations().stream().map(i -> {
            Investigation investigation = new Investigation();
            investigation.setInvestigations(i.getInvestigations());
            investigation.setPrescription(prescription);
            return investigation;
        }).collect(Collectors.toList()));

        prescription.setDiagnosis(dto.getDiagnosis().stream().map(d -> {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setDiagnosis(d.getDiagnosis());
            diagnosis.setPrescription(prescription);
            return diagnosis;
        }).collect(Collectors.toList()));

        prescription.setComplaints(dto.getComplaints().stream().map(c -> {
            Complaint complaint = new Complaint();
            complaint.setComplaints(c.getComplaints());
            complaint.setPrescription(prescription);
            return complaint;
        }).collect(Collectors.toList()));

        return prescription;
    }

    public static PrescriptionDTO toDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setName(prescription.getName());
        dto.setPhone(prescription.getPhone());
        dto.setAge(prescription.getAge());
        dto.setSex(prescription.getSex());
        dto.setAdvice(prescription.getAdvice());
        dto.setFollowup(prescription.getFollowup());

        dto.setMedicines(prescription.getMedicines().stream().map(m -> {
            PMedicineDTO medicineDTO = new PMedicineDTO();
            medicineDTO.setMedicine(m.getMedicine());
            medicineDTO.setDosage(m.getDosage());
            medicineDTO.setFrequency(m.getFrequency());
            medicineDTO.setDuration(m.getDuration());
            return medicineDTO;
        }).collect(Collectors.toList()));

        dto.setInvestigations(prescription.getInvestigations().stream().map(i -> {
            InvestigationDTO investigationDTO = new InvestigationDTO();
            investigationDTO.setInvestigations(i.getInvestigations());
            return investigationDTO;
        }).collect(Collectors.toList()));

        dto.setDiagnosis(prescription.getDiagnosis().stream().map(d -> {
            DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
            diagnosisDTO.setDiagnosis(d.getDiagnosis());
            return diagnosisDTO;
        }).collect(Collectors.toList()));

        dto.setComplaints(prescription.getComplaints().stream().map(c -> {
            ComplaintDTO complaintDTO = new ComplaintDTO();
            complaintDTO.setComplaints(c.getComplaints());
            return complaintDTO;
        }).collect(Collectors.toList()));

        return dto;
    }
}
