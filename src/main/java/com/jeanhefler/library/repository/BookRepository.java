package com.jeanhefler.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jeanhefler.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitle(@Param(value = "title") String title);
    List<Book> findByisAvaliableTrue();
}
