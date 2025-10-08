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

import com.jeanhefler.library.model.Borrow;
import com.jeanhefler.library.service.BorrowService;

@RestController
public class BorrowController {
    private BorrowService service;

    public BorrowController(BorrowService service) {
        this.service = service;
    }
    
    @PostMapping("/borrows")
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow newBorrow){
        Borrow borrow = service.createBorrow(newBorrow);
        return ResponseEntity.status(201).body(borrow);
    }

    @GetMapping("/borrows")
    public ResponseEntity<List<Borrow>> getAllBorrows(){
        List<Borrow> borrows = service.findBorrows();
        return ResponseEntity.status(200).body(borrows);
    }

    @GetMapping("/borrows/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id){
        Borrow borrow = service.findBorrowById(id);
        return ResponseEntity.status(200).body(borrow);
    }

    @GetMapping("/borrows/students")
    public ResponseEntity<List<Borrow>> getBorrowByStudents(){
        List<Borrow> borrows = service.findBorrowByStudents();
        return ResponseEntity.status(200).body(borrows);
    }

    @GetMapping("/borrows/teacher")
    public ResponseEntity<List<Borrow>> getTeachers(){
        List<Borrow> borrows = service.findBorrowByTeacher();
        return ResponseEntity.status(200).body(borrows);
    }

    @PutMapping("/borrows/{id}")
    public ResponseEntity<Borrow> putBorrow(@PathVariable Long id, @RequestBody Borrow updatedBorrow){
        Borrow borrow = service.updateBorrowById(id, updatedBorrow);
        return ResponseEntity.status(200).body(borrow);
    }

    @DeleteMapping("/borrows/{id}")
    public ResponseEntity<Borrow> deleteBorrowById(@PathVariable Long id){
        service.deleteBorrowById(id);
        return ResponseEntity.status(204).build();
    }
}
