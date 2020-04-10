package com.springbootcamp.springsecurity;


import com.springbootcamp.springsecurity.entites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() < 1) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Role admin = new Role(1L, "ROLE_ADMIN");
            Role seller = new Role(2L, "ROLE_SELLER");
            Role customer = new Role(3L, "ROLE_CUSTOMER");
            roleRepository.save(admin);
            roleRepository.save(seller);
            roleRepository.save(customer);
            Admin admin1 = new Admin("Apoorva", "Garg", "", "apoorvagarg0@gmai.com");
            admin1.setPassword(passwordEncoder.encode("pass"));
            Customer customer1 = new Customer("Daljit", "kalsi", "",
                    "daljitkalsi@gmai.com", 987654321l);
            customer1.setPassword(passwordEncoder.encode("pass"));
            Seller seller1 = new Seller("Yash", "bhatia", ""
                    , "yashbhatia@gmail.com", "yfwbeu72384627", 574148715478l,
                    "ttn");
            seller1.setPassword(passwordEncoder.encode("pass"));
            userRepository.save(admin1);
            userRepository.save(customer1);
            userRepository.save(seller1);
            System.out.println("Total users saved::" + userRepository.count());

        }
    }
}
