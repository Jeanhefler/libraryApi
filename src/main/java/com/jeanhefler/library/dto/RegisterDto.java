package com.jeanhefler.library.dto;

import com.jeanhefler.library.infra.security.UserRole;

public record RegisterDto(String name, String email, String password, UserRole role) {
    
}
