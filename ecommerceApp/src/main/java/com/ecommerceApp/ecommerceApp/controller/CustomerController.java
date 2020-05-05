package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.CustomerViewProfileDto;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.services.CustomerService;
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

//
//    @GetMapping("/activate-customer-account/{token}")
//    public String activateCustomer(@PathVariable String token){
//        String message = customerService.activateCustomer(token);
//        return message;
//    }
//    @GetMapping("/re-sent-link/{email}")
//    public String reSentLink(@PathVariable String email) {
//        String message = customerService.reSentLink(email);
//        return message;
//    }
///////////////done
        @GetMapping("/customer/view/profile")
            public CustomerViewProfileDto getprofile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getcustomerProfile(username);
    }
//////////done
    @GetMapping("/customer/getAll/addresses")
    public Set<AddressDto> getCustomerAddresses(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.getCustomerAllAdress(username);
    }
//////////done
    @PostMapping("/customer/addnew/addresses")
    public ResponseEntity addNewAddress(@Valid @RequestBody AddressDto addressDto, HttpServletRequest request, Locale locale){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.addNewAddress(username,addressDto,locale);
    }
/////////////////////done
    @PutMapping("/customer/profile")
    public ResponseEntity updateProfile(@Valid @RequestBody CustomerViewProfileDto customerViewProfileDto,
                                        HttpServletRequest httpServletRequest,Locale locale){
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomerProfile(username,customerViewProfileDto,locale);
    }
////////////////dalabase mai persist nai ho rha
    @DeleteMapping("/customer/delete/addresses/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id, HttpServletRequest request,Locale locale){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return customerService.deleteAddress(username, id,locale);
    }
///////////////////dekha ha
    @PatchMapping("/customer/update/Address/{id}")
    public ResponseEntity<String> updateCustomerAddress(@Valid @RequestBody AddressDto addressDto,
                                                        @PathVariable Long id, HttpServletRequest httpServletRequest,Locale locale)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomerAddress(username,addressDto,id,locale);

    }
    /////////done
    @PutMapping("/customer/update/password")
    public String updatePassword(@RequestBody PasswordDto passwordDto,Locale locale){
        customerService.updateCustomerPassword(passwordDto,locale);
        return messageSource.getMessage("password.updated.message",null,locale);
    }
}
