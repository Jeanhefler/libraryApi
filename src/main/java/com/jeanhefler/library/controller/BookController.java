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

import com.jeanhefler.library.model.Book;
import com.jeanhefler.library.service.BookService;

@RestController
public class BookController {
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = service.listBooks();
        return ResponseEntity.status(200).body(books); 
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = service.getBookById(id);
        return ResponseEntity.status(200).body(book);
    }

    @GetMapping("/books/avaliable")
        public ResponseEntity<List<Book>> getAvaliableBooks(){
            List<Book> books = service.getAvaliableBooks();
            return ResponseEntity.status(200).body(books);
        }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook){
        Book book = service.createBook(newBook);
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book updatedBook){
        Book book = service.updateBookById(id, updatedBook);
        return ResponseEntity.status(201).body(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id){
        service.deleteBookById(id);
        return ResponseEntity.status(204).build();
    }
}
