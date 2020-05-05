package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerDto;
import com.ecommerceApp.ecommerceApp.services.ActivationDeactivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
    ActivationDeactivationService activationDeactivationService;

    @ApiOperation(value = "shows the list of all customer")
    @GetMapping("/admin/customers/list_of_all_customers")

    public List<CustomerDto> getAllCustomer(@RequestBody(required = false) PagingAndSortingDto pagingAndSortingDto) {
        return customerService.getAllCustomer(pagingAndSortingDto);

    }

    @ApiOperation(value = "shows the list of All Sellers")
    @GetMapping("/admin/sellers/list_of_all_sellers")
    public List<SellerDto> getAllSellers(@RequestBody(required = false) PagingAndSortingDto pagingAndSortingDto) {
        return sellerService.getAllSeller(pagingAndSortingDto);
    }

    @ApiOperation(value = "This Api is used to Activated the user present with the passed Id")
    @PutMapping("/admin/activate/{id}")
    public String activateUser(@PathVariable Long id, WebRequest webRequest, Locale locale) {
        return activationDeactivationService.ActivateUser(id, webRequest, locale);
    }

    @ApiOperation(value = "This Api is used to De-Activated the user present with the passed Id")
    @PutMapping("/admin/deactivate/{id}")
    public String deActivateUser(@PathVariable Long id, WebRequest webRequest, Locale locale) {
        return activationDeactivationService.DeactivateUser(id, webRequest, locale);
    }
}