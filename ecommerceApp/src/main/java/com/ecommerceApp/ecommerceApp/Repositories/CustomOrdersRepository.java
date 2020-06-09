package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Orders;
import java.util.List;



   interface CustomOrdersRepository {

     List<Orders> yourCustomMethod();

//    @Query(value = "{ 'customerName' : ?0, }")
//    List<Orders> findAllByCustomerName(String name);
 }