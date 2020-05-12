package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.ForgetAndResetPasswordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
public class ForgotAndResetPasswordController {

    @Autowired
    ForgetAndResetPasswordService forgetAndResetPasswordService;
//////////done
@ApiOperation(value = "Api to hit the ForgetPassword",authorizations = {@Authorization(value = "Bearer")})
@PostMapping(path="/forgotPassword",produces = "application/json")
    public ReturnJson forgotPassword(@RequestBody Users users, Locale locale){
       return   forgetAndResetPasswordService.forgot_password(users.getEmail(),locale);

    }
    ////done
    @ApiOperation(value = "Api to Reset Password",authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(path="/resetPassword/{token}")
    public ReturnJson resetPassword(@Valid @RequestBody PasswordDto passwordDto, @PathVariable("token") String token,Locale locale){
        return forgetAndResetPasswordService.resetPassword(passwordDto,token,locale);
    }
}
