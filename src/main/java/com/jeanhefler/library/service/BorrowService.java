package com.jeanhefler.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.library.exception.BadRequestException;
import com.jeanhefler.library.exception.ResourceNotFound;
import com.jeanhefler.library.model.Borrow;
import com.jeanhefler.library.repository.BorrowRepository;

@Service
public class BorrowService {

    private BorrowRepository repository;

    public BorrowService(BorrowRepository repository) {
        this.repository = repository;
    }

    //Create
    public Borrow createBorrow(Borrow newBorrow){

        if(newBorrow.getName() == null){
            throw new BadRequestException("Name can't be null");
        }
        if(newBorrow.getName().length() < 3){
            throw new BadRequestException("Name can't be minor that 3 characters");
        }
        if(newBorrow.isTeacher() == false && newBorrow.getGrade() == 0){
            throw new BadRequestException("Students should be in a grade");
        }
        if(newBorrow.isTeacher() == true && newBorrow.getGrade()!= 0){
            throw new BadRequestException("Teachers should not be in a grade");
        }
        if(newBorrow.getGrade() > 9 || newBorrow.getGrade() < 0){
            throw new BadRequestException("grade range out: grade should be between 1 and 9");
        }
        
        return repository.save(newBorrow);
    }

    //read
    public List<Borrow> findBorrows(){
        return repository.findAll();
    }

    public Borrow findBorrowById(Long id){
        return repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Borrow not found"));
    }
    
    public List<Borrow> findBorrowByStudents(){
        return repository.findByisTeacherFalse();
    }

    public List<Borrow> findBorrowByTeacher(){
        return repository.findByisTeacherTrue();
    }

    //update
    public Borrow updateBorrowById(Long id, Borrow updatedBorrow){
        Borrow borrow = repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Borrow not found"));

        if(updatedBorrow.getName() != null){
            borrow.setName(updatedBorrow.getName());
        }
        if(updatedBorrow.getName().length() < 3){
            throw new BadRequestException("Name can't be minor that 3 characters");
        }
        if(updatedBorrow.getGrade() != borrow.getGrade()){
            borrow.setGrade(updatedBorrow.getGrade());
        }
        borrow.setTeacher(updatedBorrow.isTeacher());
        if(borrow.getGrade() == 0 && borrow.isTeacher() == false){
            throw new BadRequestException("Students should be in a grade");
        }
        if(borrow.isTeacher() == true && borrow.getGrade() != 0){
            throw new BadRequestException("Teachers can't be in a grade");
        }
         if(borrow.getGrade() > 9 || borrow.getGrade() < 0){
            throw new BadRequestException("grade range out: grade should be between 1 and 9");
        }
        return repository.save(borrow);
    }

    //delete
    public void deleteBorrowById(Long id){
        Borrow borrow = repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Borrow not found"));
        repository.deleteById(borrow.getId());
    }
}
