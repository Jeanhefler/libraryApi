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
import com.jeanhefler.library.model.Book;
import com.jeanhefler.library.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Book", description = "create, read, update and delete books")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class BookController {
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    
    @GetMapping("/books")
    @Operation(summary = "read books", description = "list all registered books")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = service.listBooks();
        return ResponseEntity.status(200).body(books); 
    }

    @GetMapping("/books/{id}")
    @Operation(summary = "read book by id", description = "find a specific book")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = service.getBookById(id);
        return ResponseEntity.status(200).body(book);
    }

    @GetMapping("/books/avaliable")
    @Operation(summary = "read avaliable books", description = "find avaliable books to loan")
    @ApiResponse(responseCode = "200", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<List<Book>> getAvaliableBooks(){
        List<Book> books = service.getAvaliableBooks();
        return ResponseEntity.status(200).body(books);
    }

    @PostMapping("/books")
    @Operation(summary = "create a book", description = "register a new book")
    @ApiResponse(responseCode = "201", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook){
        Book book = service.createBook(newBook);
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/books/{id}")
    @Operation(summary = "update book", description = "update a registered book")
    @ApiResponse(responseCode = "201", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book updatedBook){
        Book book = service.updateBookById(id, updatedBook);
        return ResponseEntity.status(201).body(book);
    }

    @DeleteMapping("/books/{id}")
    @Operation(summary = "delete book", description = "delete a registered book")
    @ApiResponse(responseCode = "204", description = "ok")
    @ApiResponse(responseCode = "403", description = "unauthorized")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id){
        service.deleteBookById(id);
        return ResponseEntity.status(204).build();
    }
}
