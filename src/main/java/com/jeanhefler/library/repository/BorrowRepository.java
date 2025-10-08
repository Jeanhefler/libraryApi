package com.jeanhefler.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jeanhefler.library.model.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findBorrowByName(@Param(value = "name") String name);
    List<Borrow> findByisTeacherTrue();
    List<Borrow> findByisTeacherFalse();
    
}
