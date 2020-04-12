package com.ecommerceApp.ecommerceApp.security;


import com.ecommerceApp.ecommerceApp.Repositories.RoleRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Admin;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Role;
import com.ecommerceApp.ecommerceApp.entities.Seller;
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

            Admin admin1 = new Admin("apoorvagarg0@gmail.com", "Apoorva", "", "Garg");
            admin1.setPassword(passwordEncoder.encode("pass"));

            admin1.addRole(admin);
            admin1.addRole(seller);
            admin1.setActive(true);

            Customer customer1 = new Customer("daljitkalsi@gmai.com", "Daljit", "",
                    "kalsi","9837564567" );

            customer1.setPassword(passwordEncoder.encode("pass"));

            Seller seller1 = new Seller("yashbhatia@gmail.com", "Yash", ""
                    , "bhatia", "yfwbeu72384627", "574148715478",
                    "ttn");

            seller1.setPassword(passwordEncoder.encode("pass"));
        userRepository.save(seller1);
        userRepository.save(admin1);
        userRepository.save(customer1);

            System.out.println("Total users saved::" + userRepository.count());

        }
    }
}
