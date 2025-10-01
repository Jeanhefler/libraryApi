package com.jeanhefler.library.controller;

import java.util.List;

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
    public Loan createLoan(@RequestBody Loan newLoan){
        return service.createLoan(newLoan);
    }

    @GetMapping("/loans")
    public List<Loan> getAllLoans(){
        return service.listAllLoans();
    }

    @GetMapping("/loans/{id}")
    public Loan getLoanById(@PathVariable Long id){
        return service.listLoanById(id);
    }

    @PutMapping("/loans/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan updatedLoan){
        return service.updateLoan(id, updatedLoan);
    }

    @DeleteMapping("/loans/{id}")
    public void deleteLoan(@PathVariable Long id){
        service.deleteLoan(id);
    }

}
