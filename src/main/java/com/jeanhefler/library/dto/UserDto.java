package com.jeanhefler.library.dto;

import com.jeanhefler.library.infra.security.UserRole;

public class UserDto {
    private String name;
    private String email;
    private UserRole role;

    public UserDto(){}

    public UserDto(String name, String email, UserRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    

}
