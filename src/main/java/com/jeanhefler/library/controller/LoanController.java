package com.jeanhefler.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.library.model.Loan;
import com.jeanhefler.library.service.LoanService;

@RestController
public class LoanController {
    private LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan newLoan){
        Loan loan = service.createLoan(newLoan);
        return ResponseEntity.status(201).body(loan);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getAllLoans(){
        List<Loan> loans = service.listAllLoans();
        return ResponseEntity.status(200).body(loans);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id){
        Loan loan = service.listLoanById(id);
        return ResponseEntity.status(200).body(loan);
    }

    @PutMapping("/loans/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan updatedLoan){
        Loan loan = service.updateLoan(id, updatedLoan);
        return ResponseEntity.status(200).body(loan);
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Loan> deleteLoan(@PathVariable Long id){
        service.deleteLoan(id);
        return ResponseEntity.status(204).build();
    }

}
