package com.jeanhefler.library.controller;

import java.util.List;

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
    public void createBook(@RequestBody Borrow newBorrow){
        service.createBorrow(newBorrow);
    }

    @GetMapping("/borrows")
    public List<Borrow> getAllBorrows(){
        return service.findBorrows();
    }

    @GetMapping("/borrows/{id}")
    public Borrow getBorrowById(@PathVariable Long id){
        return service.findBorrowById(id);
    }

    @GetMapping("/borrows/name/{name}")
    public List<Borrow> getBorrowByName(@PathVariable String name){
        return service.findBorrowByName(name);
    }

    @GetMapping("/borrows/teacher")
    public List<Borrow> getTeachers(){
        return service.findBorrowByTeacher();
    }

    @PutMapping("/borrows/{id}")
    public Borrow putBorrow(@PathVariable Long id, @RequestBody Borrow borrow){
        return service.updateBorrowById(id, borrow);
    }

    @DeleteMapping("/borrows/{id}")
    public void deleteBorrowById(@PathVariable Long id){
        service.deleteBorrowById(id);
    }
}
