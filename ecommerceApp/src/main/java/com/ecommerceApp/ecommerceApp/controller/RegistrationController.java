package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import com.ecommerceApp.ecommerceApp.services.ActivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
//import com.ecommerceApp.ecommerceApp.services.RegistrationService;
import com.ecommerceApp.ecommerceApp.services.RegistrationService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    ActivationService activationService;


    @PostMapping("/register/customer")
    public String registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto,
                                   WebRequest request) {

        return registrationService.registerCustomer(customerRegistrationDto, request);
    }

    @PutMapping("/activate/customer")
    public String activateCustomer(@RequestParam("token") String token, WebRequest request) {
        return activationService.activateUserByToken(token, request);
    }

    @PostMapping("/register/seller")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto){
        return registrationService.registerSeller(sellerRegistrationDto);
    }
}