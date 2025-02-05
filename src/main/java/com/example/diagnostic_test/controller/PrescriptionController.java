package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.dto.prescription.PrescriptionDTO;
import com.example.diagnostic_test.entity.Prescription.Prescription;
import com.example.diagnostic_test.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controllers
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.savePrescription(prescriptionDTO));
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }


//    @PostMapping
//    public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody PrescriptionDTO request) {
//        return ResponseEntity.ok(prescriptionService.createPrescription(request));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
//        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
//        return ResponseEntity.ok(prescriptions);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
//        Prescription prescription = prescriptionService.getPrescriptionById(id);
//        return ResponseEntity.ok(prescription);
//    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id,
//                                                           @RequestBody PrescriptionDTO request) {
//        Prescription updatedPrescription = prescriptionService.updatePrescription(id, request);
//        return ResponseEntity.ok(updatedPrescription);
//    }
//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok("Prescription deleted successfully");
    }


//    @GetMapping("/search")
//    public ResponseEntity<Page<Prescription>> searchPrescriptions(
//            @RequestParam(required = false) Long doctorId,
//            @RequestParam(required = false) String patientName,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "createdAt,desc") String sort) {
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
//        Page<Prescription> prescriptions = prescriptionService.searchPrescriptions(doctorId, patientName, pageable);
//        return ResponseEntity.ok(prescriptions);
//
//    }
}