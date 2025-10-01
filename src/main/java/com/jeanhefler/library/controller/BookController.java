package com.jeanhefler.library.controller;

import java.util.List;

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
    public List<Book> getAllBooks(){
        return service.listBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id){
        return service.getBookById(id);
    }

    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return service.getBookByTitle(title);
    }

    @GetMapping("/books/avaliable")
        public List<Book> getAvaliableBooks(){
            return service.getAvaliableBooks();
        }

    @PostMapping("/books")
    void createBook(@RequestBody Book newBook){
        service.createBook(newBook);
    }

    @PutMapping("/books/{id}")
    public Book updateBookById(@PathVariable Long id, @RequestBody Book updatedBook){
        return service.updateBookById(id, updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable Long id){
        service.deleteBookById(id);
    }
}
