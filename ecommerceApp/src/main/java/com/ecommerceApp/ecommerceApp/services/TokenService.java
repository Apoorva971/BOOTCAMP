package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.Repositories.VerificationTokenRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    UserRepository userRepository;

    public String createVerficationToken(Users user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    public void deleteVerificationToken(String token){
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        verificationTokenRepository.delete(verificationToken);
    }
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    public void saveRegisterUser(Users users) {
        userRepository.save(users);
    }

//    public String createForgotPasswordToken(Users user){
//        String token = UUID.randomUUID().toString();
//        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, user);
//        forgotPasswordRepository.save(forgotPasswordToken);
//        return token;
//    }
//
//    public ForgotPasswordToken getForgotPasswordToken(String forgotPasswordToken) {
//        return forgotPasswordRepository.findByToken(forgotPasswordToken);
//    }
//
//    public void deleteForgotToken(String token){
//        ForgotPasswordToken forgotPasswordToken=forgotPasswordRepository.findByToken(token);
//        forgotPasswordRepository.delete(forgotPasswordToken);
//    }
//    public ForgotPasswordToken getForgotPasswordToken(Users user) {
//        return forgotPasswordRepository.findByUser(user);
//    }
}