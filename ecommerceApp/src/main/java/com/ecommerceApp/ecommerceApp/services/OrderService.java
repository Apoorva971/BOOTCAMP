package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CartRepository;
import com.ecommerceApp.ecommerceApp.Repositories.OrdersRepository;
import com.ecommerceApp.ecommerceApp.dtos.OrderDto;
import com.ecommerceApp.ecommerceApp.entities.Orders;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    public OrderDto getOrderProduct(String id) {
        Orders order = ordersRepository.findById(id).get();
        return new OrderDto(order.getId(), order.getAmountPaid(), order.getDate_created(), order.getPaymentMethod(),
                order.getCustomerAddressAddressLine(), order.getCustomerAddressCity(), order.getCustomerAddressState(),
                order.getCustomerAddressCountry(), order.getCustomerAddressLabel(), order.getCustomerAddressZipCode());
    }

    public ReturnJson addNewOrder(OrderDto orderDto, Locale locale) {

        if (!cartRepository.findById(orderDto.getId()).isPresent()) {
            throw new InvalidDetailException("Item is Not Present In Cart");
        }
        Orders newOrder = new Orders(orderDto.getId(), orderDto.getAmountPaid(), orderDto.getDateCreated(),
                orderDto.getPaymentMethod(), orderDto.getCustomerAddressCity(), orderDto.getCustomerAddressState(),
                orderDto.getCustomerAddressCountry(), orderDto.getCustomerAddressAddressLine(), orderDto.getCustomerAddressZipCode(),
                orderDto.getCustomerAddressLabel());
        ordersRepository.save(newOrder);
        cartRepository.deleteById(orderDto.getId());
        return new ReturnJson(messageSource.getMessage("order.placed.message", null, locale));
    }

    public List<OrderDto> getAllOrderProducts() {
        List<Orders> orders = ordersRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.forEach(orders1 -> orderDtoList.add(new OrderDto(orders1.getId(), orders1.getAmountPaid(), orders1.getDate_created(),
                orders1.getPaymentMethod(), orders1.getCustomerAddressAddressLine(), orders1.getCustomerAddressCity(),
                orders1.getCustomerAddressState(), orders1.getCustomerAddressCountry(), orders1.getCustomerAddressLabel(),
                orders1.getCustomerAddressZipCode())));
        return orderDtoList;
    }

    public ReturnJson updateOrder(OrderDto orderDto, Locale locale) {
        if (!ordersRepository.findById(orderDto.getId()).isPresent()) {
            throw new InvalidDetailException("Item Is Not Present In Order");
        }
        Orders updateOrder = ordersRepository.findById(orderDto.getId()).get();
        updateOrder.setDate_created(orderDto.getDateCreated());
        updateOrder.setPaymentMethod(orderDto.getPaymentMethod());
        updateOrder.setAmountPaid(orderDto.getAmountPaid());
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
}