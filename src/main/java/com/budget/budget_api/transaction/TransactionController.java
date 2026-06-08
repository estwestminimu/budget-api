package com.budget.budget_api.transaction;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budget.budget_api.transaction.dto.TransactionRequest;
import com.budget.budget_api.transaction.dto.TransactionResponse;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @PathVariable Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(transactionService.getTransactions(accountId, from, to, category));
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> addTransaction(
            @PathVariable Long accountId,
            @Valid @RequestBody TransactionRequest request) {
        TransactionResponse created = transactionService.addTransaction(accountId, request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/export")
    public ResponseEntity<String> exportToCsv(@PathVariable Long accountId) {
        String csv = transactionService.exportToCsv(accountId);
        return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=transactions.csv")
        .header("Content-Type", "text/csv")
        .body(csv);

    }
    

}
