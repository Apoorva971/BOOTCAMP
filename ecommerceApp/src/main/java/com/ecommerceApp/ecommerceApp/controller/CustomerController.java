package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.CustomerViewProfileDto;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;
import java.util.Set;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    MessageSource messageSource;
///////////////done
    @ApiOperation(value = "Api to view the profile of customer", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer", produces = "application/json")
    public CustomerViewProfileDto getprofile(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getcustomerProfile(username);
    }

    //////////done
    @ApiOperation(value = "Api to view all the address of customer", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer/addresses", produces = "application/json")
    public Set<AddressDto> getCustomerAddresses(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAllAdress(username);
    }

    //////////done
    @ApiOperation(value = "Api to add new address for the customer", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/customer/addresses", produces = "application/json")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request, Locale locale) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username, addressDto, locale);
    }

    /////////////////////done
    @ApiOperation(value = "Api to Update the profile of the customer", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping(value = "/customer", produces = "application/json")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto,
                                        HttpServletRequest httpServletRequest, Locale locale) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return customerService.updateCustomerProfile(username, customerViewProfileDto, locale);
    }

    ////////////////dalabase mai persist nai ho rha
    @ApiOperation(value = "Api to delete address of customer", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping(value = "/customer/address/{id}", produces = "application/json")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id, HttpServletRequest request, Locale locale) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id, locale);
    }

    ///////////////////dekha ha
    @ApiOperation(value = "Api to Update the address of customer", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/customer/address/{id}", produces = "application/json")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto,
                                                        @PathVariable Long id, HttpServletRequest httpServletRequest, Locale locale) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return customerService.updateCustomerAddress(username, addressDto, id, locale);

    }

    /////////done
    @ApiOperation(value = "Api to Update the Password of customer", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping(value = "/customer/password", produces = "application/json")
    public String updatePassword(@RequestBody PasswordDto passwordDto, Locale locale) {
        customerService.updateCustomerPassword(passwordDto, locale);
        return messageSource.getMessage("password.updated.message", null, locale);
    }
}
