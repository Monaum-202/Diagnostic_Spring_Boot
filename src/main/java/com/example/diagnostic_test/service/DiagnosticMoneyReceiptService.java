package com.example.diagnostic_test.service;

import com.example.diagnostic_test.dto.diagnosticReceipt.DiagnosticMoneyReciptDTO2;
import com.example.diagnostic_test.dto.diagnosticReceipt.DiagnosticMoneyReciptDTo;
import com.example.diagnostic_test.entity.Doctors;
import com.example.diagnostic_test.entity.diagonesticEntry.DiagnoTests;
import com.example.diagnostic_test.entity.diagonesticEntry.DiagnosticMoneyReceipt;
import com.example.diagnostic_test.repository.DiagnosticMoneyReceiptRepository;
import com.example.diagnostic_test.repository.DiagonesticTestRepository;
import com.example.diagnostic_test.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnosticMoneyReceiptService {

    @Autowired
    private DiagnosticMoneyReceiptRepository diagnosticMoneyReceiptRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private DiagonesticTestRepository diagonesticTestRepository;


    public DiagnosticMoneyReceipt createDiagnosticMoneyReceipt(DiagnosticMoneyReciptDTo request) {
        DiagnosticMoneyReceipt diagnosticMoneyReceipt = new DiagnosticMoneyReceipt();
        Doctors doctor = doctorsRepository.findById(request.getRefBy())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        diagnosticMoneyReceipt.setRefBy(doctor);
        diagnosticMoneyReceipt.setPatientName(request.getPatientName());
        diagnosticMoneyReceipt.setAge(request.getAge());
        diagnosticMoneyReceipt.setSex(request.getSex());
        diagnosticMoneyReceipt.setMobile(request.getMobile());
        diagnosticMoneyReceipt.setCreatedBy(request.getCreatedBy());
        diagnosticMoneyReceipt.setCreatedAt(LocalDateTime.now());
        diagnosticMoneyReceipt.setTotalAmount(request.getTotalAmount());
        diagnosticMoneyReceipt.setDiscount(request.getDiscount());
        diagnosticMoneyReceipt.setPayableAmount(request.getPayableAmount());
        diagnosticMoneyReceipt.setPaidAmount(request.getPaidAmount());
        diagnosticMoneyReceipt.setDueAmount(request.getDueAmount());



        List<DiagnoTests> diagnoTests = request.getDiagonesticTests().stream().map(dts -> {
            DiagnoTests dt = new DiagnoTests();
            dt.setDiagonesticTest(diagonesticTestRepository.findById(dts.getId()).orElseThrow());
            dt.setDiagnosticMoneyReceipt(diagnosticMoneyReceipt);
            return dt;
        }).collect(Collectors.toList());

        diagnosticMoneyReceipt.setDiagonesticTests(diagnoTests);



        return diagnosticMoneyReceiptRepository.save(diagnosticMoneyReceipt);
    }



    // Get all DiagnosticMoneyReceipts
    public List<DiagnosticMoneyReceipt> getAllDiagnosticMoneyReceipts() {
        return diagnosticMoneyReceiptRepository.findAll();
    }

    // Get DiagnosticMoneyReceipt by ID
    public Optional<DiagnosticMoneyReceipt> getDiagnosticMoneyReceiptById(Long id) {
        return diagnosticMoneyReceiptRepository.findById(id);
    }

    // Update DiagnosticMoneyReceipt (or create if not exists)
    public DiagnosticMoneyReceipt updateDiagnosticMoneyReceipt(Long id, DiagnosticMoneyReciptDTo request) {
        DiagnosticMoneyReceipt existingReceipt = diagnosticMoneyReceiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnostic Money Receipt not found"));

        Doctors doctor = doctorsRepository.findById(request.getRefBy())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existingReceipt.setRefBy(doctor);
        existingReceipt.setPatientName(request.getPatientName());
        existingReceipt.setAge(request.getAge());
        existingReceipt.setSex(request.getSex());
        existingReceipt.setMobile(request.getMobile());
        existingReceipt.setCreatedBy(request.getCreatedBy());
        existingReceipt.setTotalAmount(request.getTotalAmount());
        existingReceipt.setDiscount(request.getDiscount());
        existingReceipt.setPayableAmount(request.getPayableAmount());
        existingReceipt.setPaidAmount(request.getPaidAmount());
        existingReceipt.setDueAmount(request.getDueAmount());

        List<DiagnoTests> diagnoTests = request.getDiagonesticTests().stream().map(dts -> {
            DiagnoTests dt = new DiagnoTests();
            dt.setDiagonesticTest(diagonesticTestRepository.findById(dts.getId()).orElseThrow());
            return dt;
        }).collect(Collectors.toList());
        existingReceipt.setDiagonesticTests(diagnoTests);

        return diagnosticMoneyReceiptRepository.save(existingReceipt);
    }

    // Delete DiagnosticMoneyReceipt by ID
    public void deleteDiagnosticMoneyReceipt(Long id) {
        if (!diagnosticMoneyReceiptRepository.existsById(id)) {
            throw new RuntimeException("Diagnostic Money Receipt not found");
        }
        diagnosticMoneyReceiptRepository.deleteById(id);
    }

    public Page<DiagnosticMoneyReceipt> searchDiagnosticMoneyReceipts(
            String patientName,
            String mobile,
            Long refById,
            Pageable pageable) {
        return diagnosticMoneyReceiptRepository.searchDiagnosticMoneyReceipts(patientName, mobile, refById, pageable);
    }


    // Method to get all receipts between a date range
    public List<DiagnosticMoneyReciptDTO2> getReceiptsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<DiagnosticMoneyReceipt> results = diagnosticMoneyReceiptRepository.findEventsByCreatedAtRange(startDate, endDate);


        List<DiagnosticMoneyReciptDTO2> resultsDto = new ArrayList<>();
        for (DiagnosticMoneyReceipt diagnosticMoneyReceipt:results
             ) {
            DiagnosticMoneyReciptDTO2 diagnosticMoneyReciptDTO2  = new DiagnosticMoneyReciptDTO2(
                    diagnosticMoneyReceipt.getId(),
                    diagnosticMoneyReceipt.getCreatedAt(),
                    diagnosticMoneyReceipt.getTotalAmount(),
                    diagnosticMoneyReceipt.getPayableAmount(),
                    diagnosticMoneyReceipt.getPaidAmount()
            );
            resultsDto.add(diagnosticMoneyReciptDTO2);
        }

        return resultsDto;
    }
}
