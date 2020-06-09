package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface OrdersRepository extends MongoRepository<Orders, String> , CustomOrdersRepository {
    @Query(value = "{ 'customerName' : ?0, }")
    List<Orders> findAllByCustomerName(String name);


//        @Query("db.Orders.aggregate( [ { $group : {_id : $date_created ,count : { $sum:1} } } ] )")
//    @Query(value   = "( [ { $group : {_id : $order_placed_date ,count : { $sum:1} } } ] )")
//    Json findAllByCreatedDate();

}
