package com.springbootcamp.springsecurity;

import com.springbootcamp.springsecurity.entites.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer fingByEmail(String Email);
}
