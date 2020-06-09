package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.OrderDto;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.services.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Locale;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;


    @ApiOperation(value = "Api to get Order By Id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/Order/{id}")
    public OrderDto getOrderDetails(@PathVariable String id) {
        return orderService.getOrderProduct(id);
    }

    @ApiOperation(value = "Api to place order", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/Order", produces = "application/json")
    public ReturnJson addOrder(@RequestBody OrderDto orderDto, @ApiIgnore Locale locale) {
        return orderService.addNewOrder(orderDto, locale);
    }
    @ApiOperation(value = "Api to place order", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/Order")
    public ReturnJson updateOrder(@RequestBody OrderDto orderDto,@ApiIgnore Locale locale){
        return orderService.updateOrder(orderDto,locale);
    }

    @ApiOperation(value = "Api to get All Orders", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/Order")
    public List<OrderDto> getOrdersList() {
        return orderService.getAllOrderProducts();
    }

    @ApiOperation(value = "Api to get All Orders", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping("/Order/{id}")
    public ReturnJson deleteOrder(@PathVariable String id,@ApiIgnore Locale locale){
        return  orderService.removeOrder(id,locale);
    }
    @ApiOperation(value = "to get number of orders", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/Order/getNUmber")
    public void getNUmberOfOrder(){
        orderService.AggregationExample();
    }
}
