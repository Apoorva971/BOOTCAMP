//package com.ecommerceApp.ecommerceApp.services;
//
//import com.ecommerceApp.ecommerceApp.entities.Orders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
//import org.springframework.stereotype.Service;
//
//@Service
//public class newclass {
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    public void LookupAggregationExample() {
//
//        AggregationOperation unwind = Aggregation.unwind("customerName");
//
//        String query1 = "{ $group :{from 'Orders', let: { _id : $customerName ,total_number_of_order_placed : { $sum:1} } }";
//
//        TypedAggregation<Orders> aggregation = Aggregation.newAggregation(
//                Orders.class,
//                unwind,
//                new CustomAggregationOperation(query1)
//        );
//        AggregationResults<Orders> results =
//                mongoTemplate.aggregate(aggregation, Orders.class);
//        System.out.println(results.getMappedResults());
//    }
//}
//
