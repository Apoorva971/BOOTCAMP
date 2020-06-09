package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
  @Query(value = "{ 'customerId' : ?0, }")
 List<Cart> findAllByCustomerid(Long id);
}
