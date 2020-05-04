package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.UserAttempts;
import org.springframework.data.repository.CrudRepository;

public interface UserAttemptRepository extends CrudRepository<UserAttempts,Long> {
}
