package com.ecommerceApp.ecommerceApp.dtos;


import com.ecommerceApp.ecommerceApp.validators.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRegistrationDto {
    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    @Email
    @ValidEmail
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}