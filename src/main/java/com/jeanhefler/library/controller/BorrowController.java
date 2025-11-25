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

import com.jeanhefler.library.infra.security.SecurityConfiguration;
import com.jeanhefler.library.model.Borrow;
import com.jeanhefler.library.service.BorrowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Borrow", description = "create, update, delete and find borrows")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class BorrowController {
    private BorrowService service;

    public BorrowController(BorrowService service) {
        this.service = service;
    }
    
    @PostMapping("/borrows")
    @Operation(summary = "create new borrow")
    @ApiResponse(responseCode = "201", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow newBorrow){
        Borrow borrow = service.createBorrow(newBorrow);
        return ResponseEntity.status(201).body(borrow);
    }

    @GetMapping("/borrows")
    @Operation(summary = "list all registered borrows")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<List<Borrow>> getAllBorrows(){
        List<Borrow> borrows = service.findBorrows();
        return ResponseEntity.status(200).body(borrows);
    }

    @GetMapping("/borrows/{id}")
    @Operation(summary = "read a borrow by id")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id){
        Borrow borrow = service.findBorrowById(id);
        return ResponseEntity.status(200).body(borrow);
    }

    @GetMapping("/borrows/students")
    @Operation(summary = "list all students")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<List<Borrow>> getBorrowByStudents(){
        List<Borrow> borrows = service.findBorrowByStudents();
        return ResponseEntity.status(200).body(borrows);
    }

    @GetMapping("/borrows/teacher")
    @Operation(summary = "list all teachers")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<List<Borrow>> getTeachers(){
        List<Borrow> borrows = service.findBorrowByTeacher();
        return ResponseEntity.status(200).body(borrows);
    }

    @PutMapping("/borrows/{id}")
    @Operation(summary = "update a regsitered borrow")
    @ApiResponse(responseCode = "201", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Borrow> putBorrow(@PathVariable Long id, @RequestBody Borrow updatedBorrow){
        Borrow borrow = service.updateBorrowById(id, updatedBorrow);
        return ResponseEntity.status(201).body(borrow);
    }

    @DeleteMapping("/borrows/{id}")
    @Operation(summary = "delete a registered borrow")
    @ApiResponse(responseCode = "204", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Borrow> deleteBorrowById(@PathVariable Long id){
        service.deleteBorrowById(id);
        return ResponseEntity.status(204).build();
    }
}
