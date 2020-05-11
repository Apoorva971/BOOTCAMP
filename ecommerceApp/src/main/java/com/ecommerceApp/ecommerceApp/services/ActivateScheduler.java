package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ActivateScheduler {
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ActivateScheduler.class);
    private static final DateTimeFormatter d = DateTimeFormatter.ofPattern("HH.MM.SS");
    @Scheduled(fixedRate = 20000)
//    @Scheduled(cron = "0 55 17 * * ?")
//    <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year> <command>
    public void ActivateAccount(){
        List<Users>users = userRepository.findByisActive(false);
        for (Users users1:users){
            users1.setActive(true);
            userRepository.save(users1);
        }
    }
}
