package com.ecommerceApp.ecommerceApp.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}