package com.jeanhefler.library.infra.security;

public enum UserRole {
    ADMIN("admin");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
