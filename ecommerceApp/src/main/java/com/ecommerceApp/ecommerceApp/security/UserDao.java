package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    @Autowired
    UserRepository userRepository;
    public AppUser loadUserByUsername(String username) {
        try {
            Users user = userRepository.findByEmail(username);
            return new AppUser(user.getEmail(), user.getPassword(), user.getRoles(), user.isActive());
        } catch (Exception e) {
            throw new UserNotFoundException("User not Found");
        }
    }
}
