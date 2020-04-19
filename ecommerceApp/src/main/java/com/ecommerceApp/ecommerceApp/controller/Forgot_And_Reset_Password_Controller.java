package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.dtos.UserRegistrationDto;
import com.ecommerceApp.ecommerceApp.services.Forget_And_Reset_Password_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Forgot_And_Reset_Password_Controller {

    @Autowired
    Forget_And_Reset_Password_Service forget_and_reset_password_service;

    @PostMapping(path="/forgotPassword")
    public String forgotPassword(@RequestBody UserRegistrationDto user){
        return forget_and_reset_password_service.forgot_password(user.getEmail());
    }

    @PostMapping(path="resetPassword/{token}")
    public String resetPassword(@Valid @RequestBody PasswordDto passwordDto, @PathVariable String token){
        return forget_and_reset_password_service.resetPassword(passwordDto,token);
    }
}
