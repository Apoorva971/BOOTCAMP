package com.ecommerceApp.ecommerceApp.controller;
import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerDto;
import com.ecommerceApp.ecommerceApp.services.ActivationDeactivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/////////////done
@RestController
public class AdminController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;

    @Autowired
    ActivationDeactivationService activation_deactivation_service;
    @GetMapping("/admin/customers/list_of_all_customers")

    public List<CustomerDto>getAllCustomer(@RequestBody(required = false) PagingAndSortingDto pagingAndSortingDto){
        return customerService.getAllCustomer(pagingAndSortingDto);

    }

    @GetMapping("/admin/sellers/list_of_all_sellers")
    public List<SellerDto> getAllSellers(@RequestBody(required = false) PagingAndSortingDto pagingAndSortingDto) {
        return sellerService.getAllSeller(pagingAndSortingDto);
    }

    @PutMapping("/admin/activate/{id}")
    public String activateUser(@PathVariable Long id, WebRequest webRequest,Locale locale) {
        return activation_deactivation_service.ActivateUser(id, webRequest,locale);
    }

    @PutMapping("/admin/deactivate/{id}")
    public String deActivateUser(@PathVariable Long id, WebRequest webRequest, Locale locale) {
        return activation_deactivation_service.DeactivateUser(id, webRequest,locale);
    }
}