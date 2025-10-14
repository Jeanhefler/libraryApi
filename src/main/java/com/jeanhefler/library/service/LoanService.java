package com.jeanhefler.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.library.exception.ResourceNotFound;
import com.jeanhefler.library.model.Book;
import com.jeanhefler.library.model.Borrow;
import com.jeanhefler.library.model.Loan;
import com.jeanhefler.library.repository.LoanRepository;

@Service
public class LoanService {
    private LoanRepository repository;
    private BookService bookService;
    private BorrowService borrowService;

    public LoanService(LoanRepository repository, BookService bookService, BorrowService borrowService) {
        this.repository = repository;
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    //create
    public Loan createLoan(Loan newLoan){
        Book book = bookService.getBookById(newLoan.getBook().getId());
        Borrow borrow = borrowService.findBorrowById(newLoan.getBorrow().getId());
        
        newLoan.setBook(book);
        newLoan.setBorrow(borrow);

        if(newLoan.getLoanDate() == null){
            newLoan.setLoanDate(LocalDate.now());
        }
        if(newLoan.getBook().isAvaliable() == false){
            throw new ResourceNotFound("Can't loan unavaliable books");
        }
        
        newLoan.getBook().setQuantity(newLoan.getBook().getQuantity() - 1);
        if(newLoan.getBook().getQuantity() <= 0){
            newLoan.getBook().setAvaliable(false);
        }
        return repository.save(newLoan);
    }

    //read
    public List<Loan> listAllLoans(){
        return repository.findAll();
    }

    public Loan listLoanById(Long id){
        return repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Loan not found"));
    }

    //update
    public Loan updateLoan(Long id, Loan updatedLoan){
        Loan loan = repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Loan not found"));

        if(updatedLoan.getBook() != null && updatedLoan.getBook().getId() != loan.getBook().getId()){

            updatedLoan.setBook(bookService.getBookById(id));
            if(updatedLoan.getBook().isAvaliable() == false){
                throw new ResourceNotFound("Can't loan unavaliable books");
            }
            loan.getBook().setQuantity(loan.getBook().getQuantity() + 1);

            if(loan.getBook().isAvaliable() == false){
                loan.getBook().setAvaliable(true);
            }
            updatedLoan.getBook().setQuantity(updatedLoan.getBook().getQuantity() - 1);
            if (updatedLoan.getBook().getQuantity() <= 0) {
                updatedLoan.getBook().setAvaliable(false);
            }
            
            loan.setBook(updatedLoan.getBook());
        }
        if(updatedLoan.getBorrow() != null){
            loan.setBorrow(borrowService.findBorrowById(updatedLoan.getBorrow().getId()));
        }
        if(updatedLoan.getLoanDate() != null){
            loan.setLoanDate(updatedLoan.getLoanDate());
        }
        return repository.save(loan);
    }

    //delete
    public void deleteLoan(Long id){
        Loan loan = repository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Loan not found"));
        loan.getBook().setQuantity(loan.getBook().getQuantity() + 1);
        if(loan.getBook().isAvaliable() == false){
            loan.getBook().setAvaliable(true);
        }
        repository.deleteById(id);
    }
}
