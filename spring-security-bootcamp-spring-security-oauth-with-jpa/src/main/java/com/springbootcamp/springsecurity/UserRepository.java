package com.springbootcamp.springsecurity;

import com.springbootcamp.springsecurity.entites.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String Email);

}
