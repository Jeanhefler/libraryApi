package com.jeanhefler.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.library.exception.BadRequestException;
import com.jeanhefler.library.exception.ResourceNotFound;
import com.jeanhefler.library.model.Book;
import com.jeanhefler.library.repository.BookRepository;

@Service
public class BookService {

    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }
    
    //Create
    public Book createBook(Book book){
        if(book.getTitle().length() < 3){
            throw new BadRequestException("title can't be minor that 3 characters");
        }
        if(book.getAuthor().length() < 3){
            throw new BadRequestException("author can't be minor that 3 characters");
        }
        if(book.getQuantity() > 0){
            book.setAvaliable(true);
        }
        else{
            book.setAvaliable(false);
        }
        return repository.save(book);
    }

    //Read
    public List<Book> listBooks(){
        return repository.findAll();
    }
    public Book getBookById(Long id){
        return repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Book not found"));
    }

    public List<Book> getAvaliableBooks(){
        return repository.findByisAvaliableTrue();
    }

    //Update
    public Book updateBookById(Long id, Book updatedBook){
        Book book = repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Book not found"));

        if(updatedBook.getTitle().length() < 3){
            throw new BadRequestException("title can't be minor that 3 characters");
        }
        if(book.getAuthor().length() < 3){
            throw new BadRequestException("author can't be minor that 3 characters");
        }
        if(updatedBook.getTitle() != null){
            book.setTitle(updatedBook.getTitle());
        }
        if(updatedBook.getAuthor() != null){
            book.setAuthor(updatedBook.getAuthor());
        }
        if(updatedBook.getType() != null){
            book.setType(updatedBook.getType());
        }
        if(updatedBook.getReleaseYear() != null){
            book.setReleaseYear(updatedBook.getReleaseYear());
        }
        if(updatedBook.getQuantity() != null) {
            book.setQuantity(updatedBook.getQuantity());
        }
        if(updatedBook.getQuantity() > 0) {
            book.setAvaliable(true);
        }
        else{
            book.setAvaliable(false);
        }
        return(repository.save(book));
    }

    //Delete
    public void deleteBookById(Long id){
        Book book = repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Book not found"));
        repository.deleteById(book.getId());
    }
}
