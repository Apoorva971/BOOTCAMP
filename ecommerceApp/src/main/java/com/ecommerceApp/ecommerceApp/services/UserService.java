package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;


    public String activateUserById(Long id, WebRequest request) {
        Optional<Users> user = userRepository.findById(id);
        String message;

        if (!user.isPresent()) {
            message = "No user found with this Id";
        } else {
            Users savedUser = user.get();
            if (savedUser.isActive()) {
                message = "User already active";
            } else {
                savedUser.setActive(true);
                userRepository.save(savedUser);
                String subject = "Account Activation";
                String text = "Your account has been activated successfully by our team.";
                mailService.sendEmail(savedUser.getEmail(), subject, text);

                message = "User has been activated";
            }

        }
        return message;
    }

    public String deactivateUserById(Long id, WebRequest request) {
        Optional<Users> user = userRepository.findById(id);
        String message;

        if (!user.isPresent()) {
            message = "No user found with this Id";

        } else {
            Users savedUser = user.get();
            if (!savedUser.isActive()) {
                message = "User already inactive";
            } else {
                savedUser.setActive(false);
                userRepository.save(savedUser);
                String text = "your account has been de-activated.";
                String subject = "Account De-activation";
                mailService.sendEmail(savedUser.getEmail(), subject, text);

                message = "User has been deactivated.";
            }

        }
        return message;
    }

    boolean emailExist(String email) {
        Users users = userRepository.findByEmail(email);
        if (users != null)
            return true;
        else
            return false;
    }

    boolean passwordExist(String password) {
        Users users = userRepository.findByPassword(password);
        if (users != null)
            return true;
        else
            return false;
    }



}
