package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.AddressDto;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerViewProfileDto;
import com.ecommerceApp.ecommerceApp.services.SellerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

@RestController
public class SellerController {

    @Autowired
    SellerService sellerService;
    @Autowired
    MessageSource messageSource;

    //////////done
    @ApiOperation(value = "Api to view the Profile of the Seller", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/seller/profile", produces = "application/json")
    public SellerViewProfileDto getProfileDetails(HttpServletRequest request) {
        return sellerService.getSellerProfile();
    }

    ////////////////donedone
    @ApiOperation(value = "Api to Update the profile of the Seller", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(value = "/seller/profile", produces = "application/json")
    public ResponseEntity updateProfileDetails(@RequestBody SellerViewProfileDto profileDto, HttpServletRequest request, Locale locale) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return sellerService.updateSellerProfile(username, profileDto, locale);
    }

    //////////donedone
    @ApiOperation(value = "Api to Update the address of Seller", authorizations = {@Authorization(value = "Bearer")})
    @PatchMapping(value = "/seller/address/{id}", produces = "appliation/json")
    public ResponseEntity<String> updateAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id, HttpServletRequest request, Locale locale) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return sellerService.updateSellerAddress(username, id, addressDto, locale);
    }

    ////////donedone
    @ApiOperation(value = "Api to Update the Password of the Seller", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping(value = "/seller/password", produces = "application/json")
    public String updatePassword(@RequestBody PasswordDto passwordDto, Locale locale) {
        sellerService.updatePassword(passwordDto, locale);
        return messageSource.getMessage("password.updated.message", null, locale);
    }
}
