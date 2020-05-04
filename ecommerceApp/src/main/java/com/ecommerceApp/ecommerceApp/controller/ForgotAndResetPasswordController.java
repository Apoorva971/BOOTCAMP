package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.ForgetAndResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
public class ForgotAndResetPasswordController {

    @Autowired
    ForgetAndResetPasswordService forget_and_reset_password_service;
//////////done
    @PostMapping(path="/forgotPassword")
    public String forgotPassword(@RequestBody Users users, Locale locale){
       return   forget_and_reset_password_service.forgot_password(users.getEmail(),locale);

    }
    ////done
    @PostMapping(path="/resetPassword/{token}")
    public String resetPassword(@Valid @RequestBody PasswordDto passwordDto, @PathVariable("token") String token,Locale locale){
        return forget_and_reset_password_service.resetPassword(passwordDto,token,locale);
    }
}
