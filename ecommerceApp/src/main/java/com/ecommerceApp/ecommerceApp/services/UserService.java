package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    ResponseEntity responseEntity;

    public ResponseEntity activateById(Long id, WebRequest webRequest){
        Optional<Users> user=userRepository.findById(id);
        String message;
        if(!user.isPresent()){
            message="No user found with the given id";
            responseEntity=new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        else{
            Users savedUser=user.get();
            if(savedUser.isActive()){
                message="User already active";
                responseEntity=new ResponseEntity(message,HttpStatus.BAD_REQUEST);


            }
            else {
                savedUser.setActive(true);
                userRepository.save(savedUser);
                message = "User activated";
                responseEntity = new ResponseEntity(message, HttpStatus.OK);
            }

        }
        return  responseEntity;
    }
    public  ResponseEntity deActivateById(Long id,WebRequest webRequest){
        String message;
        Optional<Users> user=userRepository.findById(id);
        if (!user.isPresent())
        {
            message = "No user found with the given id";
            responseEntity = new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        else
        {
            Users savedUser=user.get();
            if(!savedUser.isActive()){
                message="User already deactivated";
                responseEntity=new ResponseEntity(message,HttpStatus.BAD_REQUEST);

            }
            else {
                savedUser.setActive(false);
                userRepository.save(savedUser);
                message="User Deactivated";
                responseEntity = new ResponseEntity(message, HttpStatus.OK);

            }

        }
        return responseEntity;
    }

}
