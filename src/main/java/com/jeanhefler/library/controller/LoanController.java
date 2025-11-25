package com.jeanhefler.library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.library.dto.LoanDto;
import com.jeanhefler.library.dto.LoanInputDto;
import com.jeanhefler.library.exception.ResourceNotFound;
import com.jeanhefler.library.infra.security.SecurityConfiguration;
import com.jeanhefler.library.mapper.LoanMapper;
import com.jeanhefler.library.model.Loan;
import com.jeanhefler.library.service.BookService;
import com.jeanhefler.library.service.BorrowService;
import com.jeanhefler.library.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Loan", description = "create, update, delete and find loans")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class LoanController {

    private final BorrowService borrowService;

    private final BookService bookService;
    private LoanService service;

    public LoanController(LoanService service, BookService bookService, BorrowService borrowService) {
        this.service = service;
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @PostMapping("/loans")
    @Operation(summary = "create new loan")
    @ApiResponse(responseCode = "201", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Loan> createLoan(@RequestBody LoanInputDto newLoan){
        Loan loan = new Loan();
        loan.setBook(bookService.getBookById(newLoan.getBookId()));
        loan.setBorrow(borrowService.findBorrowById(newLoan.getBorrowId()));
        loan.setLoanDate(newLoan.getLoanDate());
        try {
            service.createLoan(loan);
        } catch (ResourceNotFound e) {
            return ResponseEntity.badRequest().body(null);
        }
        
        return ResponseEntity.status(201).body(loan);
    }

    @GetMapping("/loans")
    @Operation(summary = "list all loans")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<List<LoanDto>> getAllLoans(){
        List<Loan> loans = service.listAllLoans();

        List<LoanDto> loanDtos = loans.stream()
        .map(LoanMapper::toDTO)
        .collect(Collectors.toList());

        return ResponseEntity.status(200).body(loanDtos);
    }

    @GetMapping("/loans/{id}")
    @Operation(summary = "find loan by id")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable Long id){
        Loan loan = service.listLoanById(id);

        LoanDto dto = LoanMapper.toDTO(loan);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/loans/{id}")
    @Operation(summary = "update loan")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long id, @RequestBody LoanInputDto updatedLoan){
        Loan loan = new Loan();
        loan.setId(id);
        loan.setBook(bookService.getBookById(updatedLoan.getBookId()));
        loan.setBorrow(borrowService.findBorrowById(updatedLoan.getBookId()));
        loan.setLoanDate(updatedLoan.getLoanDate());

        service.updateLoan(id, loan);
        LoanDto dto = LoanMapper.toDTO(loan);
        return ResponseEntity.status(201).body(dto);
    }

    @DeleteMapping("/loans/{id}")
    @Operation(summary = "delete loan by id")
    @ApiResponse(responseCode = "204", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Loan> deleteLoan(@PathVariable Long id){
        service.deleteLoan(id);
        return ResponseEntity.status(204).build();
    }

}
