package com.ecommerceApp.ecommerceApp.Repositories;


import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByEmail(String email);

    Users findByPassword(String password);
}