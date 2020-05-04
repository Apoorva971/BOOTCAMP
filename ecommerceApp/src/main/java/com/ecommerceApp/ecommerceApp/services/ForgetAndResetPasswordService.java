package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.Repositories.VerificationTokenRepository;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import com.ecommerceApp.ecommerceApp.exceptions.PasswordNotMatchedException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class ForgetAndResetPasswordService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    MessageSource messageSource;

    public String forgot_password(String email,Locale locale) {

        System.out.println(email);

        Users users = userRepository.findByEmail(email);

        System.out.println(users.getEmail());

        if (users.getEmail() != null) {
            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setCreatedDate(new Date());
            verificationToken.setExpiryDate(new Date());
            verificationToken.setToken(token);
            verificationToken.setEmail(email);


            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("Reset Your Password");
            simpleMailMessage.setFrom("apoorvagarg30@gmail.com");

            verificationTokenRepository.save(verificationToken);
            simpleMailMessage.setTo(users.getEmail());
            simpleMailMessage.setText("To reset your password, please click on the Link given below :" + "" +
                    "\n http://localhost:8080/resetPassword/" + verificationToken.getToken());
            emailSenderService.sendEmail(simpleMailMessage);
        }
        return messageSource.getMessage("password.resetPassword.message",null,locale);
    }

    public String resetPassword(PasswordDto passwordDto, String Token, Locale locale) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(Token);
        String password = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();
        if (verificationToken.getEmail() == null) {
            return "http://localhost:8080/resetPassword?token=" + verificationToken.getToken()+ "expired";
        } else {
            if (!password.equals(confirmPassword)) {
                throw new PasswordNotMatchedException("password and confirm password doesn't matched");
            } else {
                String email = verificationToken.getEmail();

                System.out.println("just to check");
                Users users = userRepository.findByEmail(email);
                if (users.getEmail() == null) {
                    throw new UserNotFoundException("user not found");
                } else {
                    try {
                        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                        simpleMailMessage.setSubject("Account security issue");
                        simpleMailMessage.setFrom("apoorvagarg30@gmail.com");
                        simpleMailMessage.setText("To reset your password, please click on the Link given below :" + "" +
                                "\n http://localhost:8080/resetPassword?token=" + verificationToken.getToken());
                        simpleMailMessage.setTo(users.getEmail());
                        emailSenderService.sendEmail(simpleMailMessage);
                        String encodePassword = passwordEncoder.encode(password);
                        userRepository.updatePassword(encodePassword, users.getEmail());
                        verificationTokenRepository.deleteById(verificationToken.getId());
                    } catch (Exception ex) {
                        return " click the reset password link again";
                    }
                }
            }
            return messageSource.getMessage("password.changed.message",null,locale);
        }
    }
}

/*
public String forgotPassword(String email) {
//        System.out.println(email+">>>>>>>>>");
    Users user=userRepository.findByEmail(email);
//        System.out.println(user.getEmail());
    if(user.getEmail()==null)
        throw new UserNotFountException("User doesn't exist");
    else{
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setEmail(user.getEmail());
        verificationToken.setCreatedDate(new Date());
        verificationToken.setToken(token);
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
        simpleMailMessage.setSubject("RESET YOUR PASSWORD");
        simpleMailMessage.setText("Hii, \nUse this link to reset your password ->" +
                " http://localhost:8080/password/reset/"+verificationToken.getToken());
        simpleMailMessage.setTo(user.getEmail());
        emailSenderService.sendEmail(simpleMailMessage);
        verificationTokenRepository.save(verificationToken);
    }
    return "Kindly check your email to reset Password.";
}

    public String resetPassword(PasswordDto passwordDto, String token) {
        VerificationToken verificationToken = verificationTokenRepository.getByToken(token);
        String password = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();
        if (verificationToken.getEmail() == null)
            return "http://localhost:8080/password/reset/" + token + "has been expired";
        else {
            if (!password.equals(confirmPassword)) {
                throw new PasswordNotMatchedException("Password and confirm password not match");
            }
            else {
                String email = verificationToken.getEmail();
                Users user = userRepository.findByEmail(email);
                if (user.getEmail() == null)
                    throw new UserNotFountException("user not exist");
                else {
                    try {
                        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                        simpleMailMessage.setSubject("ACCOUNT SECURITY ISSUE");
                        simpleMailMessage.setText("Hii, \nYour password has been changed.");
                        simpleMailMessage.setTo(user.getEmail());
                        emailSenderService.sendEmail(simpleMailMessage);
                        String encodedPassword = passwordEncoder.encode(password);
                        userRepository.updatePassword(encodedPassword, user.getEmail());
                        verificationTokenRepository.deleteById(verificationToken.getId());
                    }
                    catch (Exception ex){
                        return "Hit the reset password link again.";
                    }
                }
            }
            return "password has changed successfully";
        }
    }


 */
