package com.example.diagnostic_test.controller;

import com.example.diagnostic_test.dto.diagnosticReceipt.DiagnosticMoneyReciptDTo;
import com.example.diagnostic_test.entity.diagonesticEntry.DiagnosticMoneyReceipt;
import com.example.diagnostic_test.service.DiagnosticMoneyReceiptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/MoneyReceipt")
public class DiagnosticMoneyReciptController {


    @Autowired
    private DiagnosticMoneyReceiptService diagnosticMoneyReceiptService;
    @PostMapping
    public ResponseEntity<DiagnosticMoneyReceipt> createPrescription(@Valid @RequestBody DiagnosticMoneyReciptDTo request) {
        return ResponseEntity.ok(diagnosticMoneyReceiptService.createDiagnosticMoneyReceipt(request));
    }

    @GetMapping
    public List<DiagnosticMoneyReceipt> getAllDiagnosticMoneyReceipts() {
        return diagnosticMoneyReceiptService.getAllDiagnosticMoneyReceipts();
    }

    // GET DiagnosticMoneyReceipt by ID
    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticMoneyReceipt> getDiagnosticMoneyReceiptById(@PathVariable Long id) {
        DiagnosticMoneyReceipt receipt = diagnosticMoneyReceiptService.getDiagnosticMoneyReceiptById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Diagnostic Money Receipt not found"));
        return ResponseEntity.ok(receipt);
    }

    // CREATE or UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticMoneyReceipt> updateDiagnosticMoneyReceipt(
            @PathVariable Long id,
            @RequestBody DiagnosticMoneyReciptDTo request) {
        DiagnosticMoneyReceipt updatedReceipt = diagnosticMoneyReceiptService.updateDiagnosticMoneyReceipt(id, request);
        return ResponseEntity.ok(updatedReceipt);
    }

    // DELETE DiagnosticMoneyReceipt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosticMoneyReceipt(@PathVariable Long id) {
        diagnosticMoneyReceiptService.deleteDiagnosticMoneyReceipt(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<Page<DiagnosticMoneyReceipt>> searchDiagnosticMoneyReceipts(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) Long refById,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        Page<DiagnosticMoneyReceipt> receipts = diagnosticMoneyReceiptService.searchDiagnosticMoneyReceipts(patientName, mobile, refById, pageable);
        return ResponseEntity.ok(receipts);
    }
}
