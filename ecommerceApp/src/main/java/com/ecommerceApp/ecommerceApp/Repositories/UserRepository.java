package com.ecommerceApp.ecommerceApp.Repositories;


import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByEmail(String email);
    Optional<Users> findById(Long id);
    Users findByPassword(String password);
}