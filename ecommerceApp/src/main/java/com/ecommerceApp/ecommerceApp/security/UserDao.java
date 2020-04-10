package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {
    @Autowired
    UserRepository userRepository;

    public AppUser loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        List<GrantAuthorityImpl> grantAuthorityList = new ArrayList<>();
        List<Role> roles = user.getRoles();

        roles.forEach(role ->
                {
                    grantAuthorityList.add(new GrantAuthorityImpl(role.getAuthority()));
                }
        );
        if (username != null) {
            return new AppUser(user.getEmail(),
                    user.getPassword(),
                    grantAuthorityList);
        } else {
            throw new RuntimeException();
        }
    }
}