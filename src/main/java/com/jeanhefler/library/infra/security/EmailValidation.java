package com.jeanhefler.library.infra.security;

import org.springframework.stereotype.Component;

@Component
public class EmailValidation {
    public boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailRegex);
    }
}
