package com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.dto;

public class UserLoginDto {

    private String email;
    private String password;

    // Constructor vacío
    public UserLoginDto() {}

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

