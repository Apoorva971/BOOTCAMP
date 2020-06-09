package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.Repositories.CustomOrdersRepository;
import com.ecommerceApp.ecommerceApp.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;

import java.util.List;

 class CustomOrdersRepositoryImpl implements CustomOrdersRepository {
    @Autowired
    private MongoOperations mongoOperations;
    public List<Orders> yourCustomMethod() {

        // custom match queries here
        GroupOperation group = null;
        // Group by , Lookup others stuff goes here
        // For details: https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/core/aggregation/Aggregation.html

        Aggregation aggregate = Aggregation.newAggregation(group);

        AggregationResults<Orders> orderAggregate = mongoOperations.aggregate(aggregate,
                Orders.class, Orders.class);
        return orderAggregate.getMappedResults();

    }
}