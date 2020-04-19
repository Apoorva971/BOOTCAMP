package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.Repositories.RoleRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.*;
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role admin = new Role( 1l,"ROLE_ADMIN");
        Role seller = new Role( 2l,"ROLE_SELLER");
        Role customer = new Role( 3l,"ROLE_CUSTOMER");

        roleRepository.save(admin);
        roleRepository.save(seller);
        roleRepository.save(customer);

        Admin admin1 = new Admin("chiragtest9654@gmail.com", "Apoorva", "", "Garg");
        admin1.setPassword(passwordEncoder.encode("apoorvagarg"));

        admin1.isEnabled(true);
        admin1.setAccountNonExpired(true);
        admin1.setAccountNonLocked(true);
        admin1.setCredentialsNonExpired(true);
        admin1.setEnabled(true);
        admin1.setActive(true);
        admin1.addRole(admin);
        admin1.addRole(seller);
        admin1.addAddress(new Address("1153", "Bulanshahar", "Uttar Pradesh", "202394", "India","Home"));
        admin1.setActive(true);

        Customer customer1 = new Customer("daljitkalsi@gmail.com", "Daljit", "",
                "kalsi", "9837564567");

        customer1.setPassword(passwordEncoder.encode("daljitkalsi"));
        customer1.addAddress(new Address("112", "Punjab", "Punjab", "12345", "India","P.G"));
        customer1.addAddress(new Address("101", "Chandigarh", "Punjab", "202344", "India","Home"));

        customer1.isEnabled(true);
        customer1.setAccountNonExpired(true);
        customer1.setAccountNonLocked(true);
        customer1.setCredentialsNonExpired(true);
        customer1.setEnabled(true);
        customer1.setActive(true);
        Seller seller1 = new Seller("yashbhatia@gmail.com", "Yash", ""
                , "bhatia", "yfwbeu72384627", "574148715478",
                "ttn");
        seller1.setPassword(passwordEncoder.encode("yashbhatia"));
        seller1.addAddress(new Address("110", "Dadri", "Uttar Pradesh", "101310", "India","office"));

        seller1.isEnabled(true);
        seller1.setAccountNonExpired(true);
        seller1.setAccountNonLocked(true);
        seller1.setCredentialsNonExpired(true);
        seller1.setEnabled(true);
        seller1.setActive(true);
        userRepository.save(seller1);
        userRepository.save(admin1);
        userRepository.save(customer1);

        System.out.println("Total users saved::" + userRepository.count());

    }
}
