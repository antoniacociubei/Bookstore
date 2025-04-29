package com.example.bookstore.dto;

public class PasswordResetDto {

    private String token;
    private String newPassword;

    // Getters, setters, and any required constructors...

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

