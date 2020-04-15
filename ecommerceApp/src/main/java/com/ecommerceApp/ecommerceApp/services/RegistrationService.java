//package com.ecommerceApp.ecommerceApp.services;
//
//import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
//import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
//import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
//import com.ecommerceApp.ecommerceApp.entities.Customer;
//import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.context.request.WebRequest;
//
//public class RegistrationService {
//    @Autowired
//    CustomerRepository customerRepository;
//    @Autowired
//    TokenService tokenService;
//
//    @Autowired
//    MailService mailService;
//    @Autowired
//    CustomerService customerService;
//    @Autowired
//    SellerService sellerService;
//
//    @Autowired
//    ActivationService activationService;
//
//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    @Autowired
//    UserRepository userRepository;
//
//    public String registerCustomer(CustomerRegistrationDto customerRegistrationDto, WebRequest webRequest)
//    {
//        Customer customer = customerRepository.findByEmail(customerRegistrationDto.getEmail());
//        if(customer!=null)
//            throw new EmailAlreadyExistsException("email id already exist");
//        else
//        {
//            Customer newCustomer = customerService.toCustomer(customerRegistrationDto);
//            newCustomer.setPassword((passwordEncoder.encode(newCustomer.getPassword())));
//            Customer savedCustomer = customerRepository.save(newCustomer);
//            String subject="Accounr Registration";
//            String aaUrl = WebRequest.getContextPath();
//            activationService.sendActivationLinkMail(appUrl,savedCustomer,subject);
//            String message = "Your account has been created, an activation link has ben sent to your given email";
//            return message;
//        }
//    }
//}
