package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.VerificationTokenRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    EmailSenderService mailService;
    Customer initializeNewCustomer(Customer customer) {
        customer.setActive(false);
        customer.setDeleted(false);
        customer.setAccountNonExpired(true);
        customer.setAccountNonLocked(true);
        customer.setCredentialsNonExpired(true);
        customer.setEnabled(true);

        return customer;
    }

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity createNewCustomer(CustomerRegistrationDto customerRegistrationDto) throws
            EmailAlreadyExistsException {

        Customer customer = modelMapper.map(customerRegistrationDto, Customer.class);

        if (customerRepository.findByEmail(customer.getEmail()) != null) // User already exists with this email
            throw new EmailAlreadyExistsException("User already exists with email : " + customer.getEmail());
        else {
            customerRepository.save(initializeNewCustomer(customer));   // saving the Customer

            VerificationToken verificationToken = new VerificationToken(customer);
            verificationTokenRepository.save(verificationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Complete Registration of the Customer!");
            mailMessage.setFrom("apoorvagarg0@gmail.com");
            mailMessage.setText("To confirm your account, please click on the Link given below : "
                    + "http://localhost:8080/register/confirm?token=" + verificationToken.getToken());

            mailService.sendEmail(mailMessage);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public String validateRegistrationToken(String userToken) {

        VerificationToken foundToken = verificationTokenRepository.findByToken(userToken);

        if (foundToken != null) {
            Customer customer = customerRepository.findByEmail(foundToken.getUser().getEmail());
            customer.setEnabled(true);
            customerRepository.save(customer);
            return "account verified";
        }
        return "something went wrong!";
    }


}
