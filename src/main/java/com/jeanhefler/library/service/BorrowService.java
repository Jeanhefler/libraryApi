package com.jeanhefler.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.library.model.Borrow;
import com.jeanhefler.library.repository.BorrowRepository;

@Service
public class BorrowService {

    private BorrowRepository repository;

    public BorrowService(BorrowRepository repository) {
        this.repository = repository;
    }

    //Create
    public void createBorrow(Borrow newBorrow){
        if(newBorrow.isTeacher() == true){
            newBorrow.setGrade(null);
        }
        else if(newBorrow.isTeacher() == false && newBorrow.getGrade() == null){
            throw new IllegalArgumentException("Alunos devem ter uma série definida");
        }
        repository.save(newBorrow);
    }

    //read
    public List<Borrow> findBorrows(){
        return repository.findAll();
    }

    public Borrow findBorrowById(Long id){
        return repository.findById(id).get();
    }
    
    public List<Borrow> findBorrowByName(String name){
        return repository.findBorrowByName(name);
    }

    public List<Borrow> findBorrowByTeacher(){
        return repository.findByisTeacherTrue();
    }

    //update
    public Borrow updateBorrowById(Long id, Borrow updatedBorrow){
        Borrow borrow = repository.findById(id).get();

        borrow.setTeacher(updatedBorrow.isTeacher());

        if(updatedBorrow.isTeacher() == true){
            borrow.setGrade(null);
        }
        else if(updatedBorrow.isTeacher() == false && updatedBorrow.getGrade() == null){
            throw new IllegalArgumentException("Alunos devem ter uma série definida");
        }
        if(updatedBorrow.getName() != null){
            borrow.setName(updatedBorrow.getName());
        }
        if(updatedBorrow.getClass() != null){
            borrow.setGrade(updatedBorrow.getGrade());
        }
        return repository.save(borrow);
    }

    //delete
    public void deleteBorrowById(Long id){
        repository.deleteById(id);
    }
}
