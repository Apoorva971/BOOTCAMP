package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CartRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.OrdersRepository;
import com.ecommerceApp.ecommerceApp.dtos.OrderDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Orders;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    public Customer getLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Customer customer = customerRepository.findByEmail(username);
        return customer;
    }

    public OrderDto getOrderProduct(String id) {
        Orders order = ordersRepository.findById(id).get();
        return new OrderDto(order.getId(), order.getAmountPaid(), order.getCustomerName(), order.getCartList(), order.getOrder_placed_date(), order.getPaymentMethod(),
                order.getCustomerAddressAddressLine(), order.getCustomerAddressCity(), order.getCustomerAddressState(),
                order.getCustomerAddressCountry(), order.getCustomerAddressLabel(), order.getCustomerAddressZipCode());
    }

    public ReturnJson addNewOrder(OrderDto orderDto, Locale locale) {
        if (!cartRepository.findById(orderDto.getId()).isPresent()) {
            throw new InvalidDetailException("Item is Not Present In Cart");
        }
//        Orders newOrder = new Orders(orderDto.getId(),orderDto.getCustomer().getId(),orderDto.getCustomer().getContact(),
//                orderDto.getCustomer().getAddresses(),orderDto.getAmountPaid(), orderDto.getOrder_placed_date(),
//                orderDto.getPaymentMethod(), orderDto.getCustomerAddressCity(), orderDto.getCustomerAddressState(),
//                orderDto.getCustomerAddressCountry(), orderDto.getCustomerAddressAddressLine(),
//                orderDto.getCustomerAddressZipCode(),
//                orderDto.getCustomerAddressLabel());
        Orders newOrder = new Orders();
        newOrder.setId(orderDto.getId());
        newOrder.setCustomerAddressZipCode(orderDto.getCustomerAddressZipCode());
        newOrder.setAmountPaid(orderDto.getAmountPaid());
        newOrder.setPaymentMethod(orderDto.getPaymentMethod());
        newOrder.setOrder_placed_date(orderDto.getOrder_placed_date());
        newOrder.setCartList(orderDto.getCartList());
        newOrder.setCustomerName(getLoggedInCustomer().getFirstName());
        newOrder.setCustomerAddressAddressLine(orderDto.getCustomerAddressAddressLine());
        newOrder.setCustomerAddressCity(orderDto.getCustomerAddressCity());
        newOrder.setCustomerAddressCountry(orderDto.getCustomerAddressCountry());
        newOrder.setCustomerAddressLabel(orderDto.getCustomerAddressLabel());
        newOrder.setCustomerAddressState(orderDto.getCustomerAddressState());
        newOrder.setCustomerAddressZipCode(orderDto.getCustomerAddressZipCode());
        ordersRepository.save(newOrder);
        cartRepository.deleteById(orderDto.getId());
        return new ReturnJson(messageSource.getMessage("order.placed.message", null, locale));
    }

    public List<OrderDto> getAllOrderProducts() {
        List<Orders> orders = ordersRepository.findAllByCustomerName(getLoggedInCustomer().getFirstName());
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.forEach(orders1 -> orderDtoList.add(new OrderDto(orders1.getId(), orders1.getCartList(), orders1.getCustomerName(), orders1.getAmountPaid(), orders1.getOrder_placed_date(),
                orders1.getPaymentMethod(), orders1.getCustomerAddressAddressLine(), orders1.getCustomerAddressCity(),
                orders1.getCustomerAddressState(), orders1.getCustomerAddressCountry(), orders1.getCustomerAddressLabel(),
                orders1.getCustomerAddressZipCode())));
        System.out.println("total number of order placed =" + ordersRepository.count());
        return orderDtoList;
    }

    public ReturnJson updateOrder(OrderDto orderDto, Locale locale) {
        if (!ordersRepository.findById(orderDto.getId()).isPresent()) {
            throw new InvalidDetailException("Item Is Not Present In Order");
        }
        Orders updateOrder = ordersRepository.findById(orderDto.getId()).get();
        updateOrder.setOrder_placed_date(orderDto.getOrder_placed_date());
        updateOrder.setPaymentMethod(orderDto.getPaymentMethod());
        updateOrder.setAmountPaid(orderDto.getAmountPaid());
        updateOrder.setCustomerName(getLoggedInCustomer().getFirstName());
        updateOrder.setCartList(orderDto.getCartList());
        updateOrder.setCustomerAddressCity(orderDto.getCustomerAddressCity());
        updateOrder.setCustomerAddressState(orderDto.getCustomerAddressState());
        updateOrder.setCustomerAddressCountry(orderDto.getCustomerAddressCountry());
        updateOrder.setCustomerAddressAddressLine(orderDto.getCustomerAddressAddressLine());
        updateOrder.setCustomerAddressLabel(orderDto.getCustomerAddressLabel());
        updateOrder.setCustomerAddressZipCode(orderDto.getCustomerAddressZipCode());
        ordersRepository.save(updateOrder);
        return new ReturnJson(messageSource.getMessage("order.updated.message", null, locale));
    }

    public ReturnJson removeOrder(String id, Locale locale) {
        if (!ordersRepository.findById(id).isPresent()) {
            return new ReturnJson(messageSource.getMessage("order.notPresent.message", null, locale));
        } else {
            ordersRepository.deleteById(id);
            return new ReturnJson(messageSource.getMessage("order.deleted.message", null, locale));
        }
    }

public void AggregationExample() {

    AggregationOperation unwind = Aggregation.unwind("customerName");

    String query1 = "{$lookup: {from: 'Orders', let: { id: { $toObjectId: 'customerName' },"
            +"pipeline:[{ $group : { _id : $customerName ,total_number_of_order_placed : { $sum:1} } }]";

    TypedAggregation<Orders> aggregation = Aggregation.newAggregation(
            Orders.class,
            unwind,
            new CustomAggregationOperation(query1)
    );
    AggregationResults<Orders> results =
            mongoTemplate.aggregate(aggregation, Orders.class);
    System.out.println(results.getMappedResults());
}
}