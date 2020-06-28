package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class ActivateScheduler {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ActivateScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//        @Scheduled(fixedRate = 50000)
////    @Scheduled(cron = "0 55 17 * * ?")
////    <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year> <command>
//    public void ActivateAccount() {
//        List<Users> users = userRepository.findByisActive(false);
//        for (Users users1 : users) {
//            users1.setActive(true);
//            userRepository.save(users1);
//        }
    @Scheduled(cron = "0 30 13 * * ? ")
//    @Scheduled(fixedRate = 20000)
    public void productSchedular() {
        List<Customer> customerList = customerRepository.findAll();
        if (!customerList.isEmpty()) {
            Customer customer = customerList.get(0);
            Iterable<ProductVariation> productVariations = productVariationRepository.findAll();
            for (ProductVariation productVariation : productVariations) {
                if (productVariation.getProduct().getId() == 1) {
                    if (productVariation.getPrice() > 2000) {
                        productVariation.setPrice(1000l);
                        productVariationRepository.save(productVariation);
                        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                        simpleMailMessage.setSubject("Regarding Product Offer");
                        simpleMailMessage.setFrom("apoorvagarg30@gmail.com");
                        simpleMailMessage.setText("hii we have an offer for you on " + productVariation.getProduct().getName() + " its price is" + productVariation.getPrice() + " only.Get it today");
                        simpleMailMessage.setTo(customer.getEmail());
                        emailSenderService.sendEmail(simpleMailMessage);
                    } else {
                        System.out.println(customer.getEmail());
                        System.out.println("price is less than 2000 no offer available");
                    }
                }
            }
        } else {
            System.out.println("customerList");
        }
    }
    @Scheduled(cron = "0 50 14 * * ?")
    public void passwordExpired() {
        Iterable<Users> userList =  userRepository.findAll();
        for (Users user : userList) {
            LocalDate currentDate = LocalDate.now();
            if (user.getCreatedDate() != null) {
                LocalDate updatedDate= user.getCreatedDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                // Date updated = new Date(user.getUpdatePasswordDate().getYear(), user.getUpdatePasswordDate().getMonth(), user.getUpdatePasswordDate().getDate());
                if (currentDate.equals(updatedDate.plusMonths(6))) {
                    user.setPasswordExpired(true);
                    System.out.println(updatedDate);
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setFrom("apoorvagarg30@gmail.com");
                    simpleMailMessage.setSubject("Reminder of password expiration");
                    simpleMailMessage.setText("Hi, \\n As per terms your password has been expired. Click here to reset! http://localhost:8080/forgotPassword");
                    simpleMailMessage.setTo(user.getEmail());
                    emailSenderService.sendEmail(simpleMailMessage);
                    userRepository.save(user);
                }
            }
        }
    }
}
