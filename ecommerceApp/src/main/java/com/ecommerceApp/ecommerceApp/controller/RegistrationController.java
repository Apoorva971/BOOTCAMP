package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SellerService sellerService;
    @Autowired
    SellerRepository sellerRepository;

    @PostMapping("/register/customer")
    public String registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerRegistrationDto,
                                   WebRequest request) {

        Customer customer = customerRepository.findByEmail(customerRegistrationDto.getEmail());

        if (customer != null)
            throw new EmailAlreadyExistsException("email id already exists");

        else {
            Customer newCustomer = customerService.toCustomer(customerRegistrationDto);
            Customer savedCustomer = customerRepository.save(newCustomer);
            System.out.println("customer registered successfully.");

            return "success";
        }
    }

    @PostMapping("/register/seller")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerRegistrationDto,
                                 WebRequest webRequest) {

        Seller seller = sellerRepository.findByEmail(sellerRegistrationDto.getEmail());

        if (seller != null)
            throw new EmailAlreadyExistsException("email id already exists");

        else {
            Seller newSeller = sellerService.toSeller(sellerRegistrationDto);
            Seller savedSeller = sellerRepository.save(newSeller);
            System.out.println("seller registered successfully.");
            return "success";
        }
    }
}