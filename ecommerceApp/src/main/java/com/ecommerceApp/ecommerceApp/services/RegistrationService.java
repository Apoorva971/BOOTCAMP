package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.RoleRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Role;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import com.ecommerceApp.ecommerceApp.services.ActivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.MailService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SellerService sellerService;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ActivationService activationService;
    @Autowired
    RoleRepository roleRepository;

    public String registerCustomer(CustomerRegistrationDto customerRegistrationDto, WebRequest webRequest) {
        Customer customer = customerRepository.findByEmail(customerRegistrationDto.getEmail());

        if (customer != null)
            throw new EmailAlreadyExistsException("email id already exists");

        else {
            Customer newCustomer = customerService.toCustomer(customerRegistrationDto);
            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(roleRepository.findByAuthority("ROLE_CUSTOMER"));
            newCustomer.setRoles(roleSet);
            Customer savedCustomer = customerRepository.save(newCustomer);
            String subject = "Account Registration.";
            String appUrl = webRequest.getContextPath();
            activationService.sendActivationLinkMail(appUrl,savedCustomer,subject);
            String message = "Your account has been created, link has been sent to your email id.";
            return message;

        }
    }


    public String registerSeller(SellerRegistrationDto sellerRegistrationDto){
        String message=sellerService.checkIfUnique(sellerRegistrationDto);
        if(!(message=="unique")){
            return "Invalid data";
        }
        Seller seller=sellerService.toSeller(sellerRegistrationDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepository.save(seller);
        sellerService.acknowledgementEmail(seller.getEmail());
        return  "Account created successfully.";

    }

}