package com.ecommerceApp.ecommerceApp.services;
import com.ecommerceApp.ecommerceApp.Repositories.UserAttemptRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.UserAttempts;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ActivationDeactivationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    UserAttemptRepository userAttemptRepository;
    @Autowired
    MessageSource messageSource;

    public String ActivateUser(Long id, WebRequest request,Locale locale) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return messageSource.getMessage("user.Invalid.message",null,locale);
        } else {
            Users saveUser = user.get();
            if (saveUser.isActive()) {
                return messageSource.getMessage("account.alreadyactivated.message",null,locale);
            } else {
                saveUser.setActive(true);
                saveUser.setAccountNonLocked(true);
                userRepository.save(saveUser);
                UserAttempts userAttempts1 = new UserAttempts();
                userAttempts1.setAttempts(0);
                userAttemptRepository.save(userAttempts1);
                userAttemptRepository.save(userAttempts1);

                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("Account Activation");
                simpleMailMessage.setText("your account is activated successfully");
                simpleMailMessage.setTo(saveUser.getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                return messageSource.getMessage("account.activated.message",null,locale);
            }

        }
    }
    public String DeactivateUser(Long id, WebRequest request, Locale locale) {
        Optional<Users> user = userRepository.findById(id);
        System.out.println(user.get().getId());

        if(user.isPresent()==false){
            throw new NoSuchElementException(messageSource.getMessage("user.Invalid.message",null,locale));
        } else {
            Users saveUser = user.get();
            if (saveUser.isActive()==false) {
                return messageSource.getMessage("account.alreadydeactivated.message",null,locale);
            } else {
                saveUser.setActive(false);
                userRepository.save(saveUser);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("Account De-activation");
                simpleMailMessage.setText("your account is de-activated successfully");
                simpleMailMessage.setTo(saveUser.getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
               return messageSource.getMessage("account.deactivated.message",null,locale);
            }
        }
    }
}
