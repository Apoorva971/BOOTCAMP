package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerDto;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.services.ActivationDeactivationService;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation(value = "Api to display the list of all customer", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/admin/customers", produces = "application/json")

    public List<CustomerDto> getAllCustomer(@RequestParam(required = false) PagingAndSortingDto pagingAndSortingDto) {
        return customerService.getAllCustomer(pagingAndSortingDto);

    }

    @ApiOperation(value = "Api to display the list of All Sellers", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/admin/sellers", produces = "application/json")
    public List<SellerDto> getAllSellers(@RequestParam(required = false) PagingAndSortingDto pagingAndSortingDto) {
        return sellerService.getAllSeller(pagingAndSortingDto);
    }

    @ApiOperation(value = "Api is used to Activated the user", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping(value = "/admin/activate/{id}", produces = "application/json")
    public ReturnJson activateUser(@PathVariable Long id,@ApiIgnore WebRequest webRequest, @ApiIgnore Locale locale) {
        return activationDeactivationService.ActivateUser(id, webRequest, locale);
    }

    @ApiOperation(value = "This Api is used to De-Activated the user",authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/admin/deactivate/{id}")
    public ReturnJson deActivateUser(@PathVariable Long id,@ApiIgnore WebRequest webRequest, @ApiIgnore Locale locale) {
        return activationDeactivationService.DeactivateUser(id, webRequest, locale);
    }
}