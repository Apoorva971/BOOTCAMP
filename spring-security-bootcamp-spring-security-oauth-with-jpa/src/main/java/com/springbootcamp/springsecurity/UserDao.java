package com.springbootcamp.springsecurity;

import com.springbootcamp.springsecurity.entites.Role;
import com.springbootcamp.springsecurity.entites.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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