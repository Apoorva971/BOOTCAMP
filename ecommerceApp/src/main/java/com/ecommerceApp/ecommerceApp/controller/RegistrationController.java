package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
@ApiOperation(value = "Api to Register a new customer",authorizations = {@Authorization(value = "Bearer")})

@PostMapping(value = "/register/customer",produces = "application/json")
    String registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto, Locale locale) {
        return customerService.createNewCustomer(customerRegistrationDto,locale);
    }
    @ApiOperation(value = "Api to Activate the new registered customer",authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/activate/customer/account/{token}",produces = "application/json")
    public String activateCustomer(@PathVariable String token){
        String message = customerService.activateCustomer(token);
        return message;
    }
    @ApiOperation(value = "Api to get the Re-sent link",authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/re-sent-link/{email}",produces = "application/json")
    public String reSentLink(@PathVariable String email) {
        String message = customerService.reSentLink(email);
        return message;
    }
////////////done
@ApiOperation(value = "Api to confirm the acount of the customer",authorizations = {@Authorization(value = "Bearer")})

@GetMapping(value = "register/confirm",produces = "application/json")
    String confirmRegistration(@RequestParam("token") String token) {

        return customerService.validateRegistrationToken(token);

    }
/////////////done
@ApiOperation(value = "Api to Register a new Seller",authorizations = {@Authorization(value = "Bearer")})

@PostMapping(value = "/register/seller",produces = "application/json")
    String registerseller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto,Locale locale){
        return sellerService.registerSeller(sellerRegistrationDto,locale);
    }


}