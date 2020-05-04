package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
public class RegistrationController {

    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
//////////done
    @PostMapping("/register/customer")
    ResponseEntity registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto, Locale locale) {
        return customerService.createNewCustomer(customerRegistrationDto,locale);
    }
////////////done
    @GetMapping("register/confirm")
    String confirmRegistration(@RequestParam("token") String token) {

        return customerService.validateRegistrationToken(token);

    }
/////////////done
    @PostMapping("/register/seller")
    String registerseller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto,Locale locale){
        return sellerService.registerSeller(sellerRegistrationDto,locale);
    }


}