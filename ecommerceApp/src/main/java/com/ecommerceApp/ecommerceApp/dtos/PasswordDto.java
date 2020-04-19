package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.validators.PasswordMatches;
import com.ecommerceApp.ecommerceApp.validators.ValidPassword;

import javax.validation.constraints.NotNull;

public class PasswordDto {
    @NotNull
    @ValidPassword
    private String password;
    @NotNull
    @ValidPassword
    private String confirmPassword;

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
