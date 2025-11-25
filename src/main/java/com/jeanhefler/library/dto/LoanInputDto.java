package com.jeanhefler.library.dto;

import java.time.LocalDate;

public class LoanInputDto {
    private Long bookId;
    private Long borrowId;
    private LocalDate loanDate;

    public LoanInputDto() {}
    public LoanInputDto(Long bookId, Long borrowId, LocalDate loanDate) {
        this.bookId = bookId;
        this.borrowId = borrowId;
        this.loanDate = loanDate;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }
    
}
