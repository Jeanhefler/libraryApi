package com.jeanhefler.library.mapper;

import com.jeanhefler.library.dto.LoanDto;
import com.jeanhefler.library.model.Loan;

public class LoanMapper {
    public static LoanDto toDTO(Loan loan){
        return new LoanDto(
            loan.getId(),
            loan.getBook().getId(),
            loan.getBorrow().getId(),
            loan.getBook().getTitle(),
            loan.getBorrow().getName(),
            loan.getLoanDate()
            );
    }
}
