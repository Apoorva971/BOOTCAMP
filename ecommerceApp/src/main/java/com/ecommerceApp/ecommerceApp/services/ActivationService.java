package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

public class ActivationService {
    @Autowired
    TokenService tokenService;
    @Autowired
    MailService mailService;
     public String activateUserByToken(String token, WebRequest webRequest){
         VerificationToken verificationToken = tokenService.getVerificationToken(token);
         if(verificationToken == null){
             return "Invalid token";
         }
         String message;
         Users user = verificationToken.getUser();
         Calendar calendar = Calendar.getInstance();
         if((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
             message = "your activation link has been expired. so we have sent a new link to your+"
             + "registerred email";
             String appUrl = webRequest.getContextPath();
             tokenService.deleteVerificationToken(token);
             sendActivationLinkMail(appUrl, user, "Account Re-Activation Link");
         }
         user.setActive(true);
         tokenService.saveRegisterUser(user);
         tokenService.deleteVerificationToken(token);
         message = "Activated";
         return message;
         }

    public void sendActivationLinkMail(String appUrl, Users users, String subject) {

        String token = tokenService.createVerficationToken(users);

        String email = users.getEmail(); //username have been reffered as email
        String confirmationUrl = "http://localhost:8080" + appUrl +
                "/activate/customer?token=" + token;
        String message = "please activate your account \r\n" + confirmationUrl;
        mailService.sendEmail(email, subject, message);
    }

}

