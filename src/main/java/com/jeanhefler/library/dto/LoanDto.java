package com.jeanhefler.library.dto;

import java.time.LocalDate;

public class LoanDto {
    private Long loanId;
    private Long bookId;
    private Long borrowId;
    private String bookTitle;
    private String borrowName;
    private LocalDate loanDate;

    public LoanDto(){}

    public LoanDto(Long loanId, Long bookId, Long borrowId, String bookTitle, String borrowName, LocalDate loanDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.borrowId = borrowId;
        this.bookTitle = bookTitle;
        this.borrowName = borrowName;
        this.loanDate = loanDate;
    }

    public Long getLoanId() {
        return loanId;
    }
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBorrowName() {
        return borrowName;
    }
    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
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

}
