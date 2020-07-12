package com.ecommerceApp.ecommerceApp.controller;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.newclass;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class CriteriaController {
    @Autowired
    newclass newclass;
    @ApiOperation(value = "This Api is used to show the working of criteria query", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/criteria/{id}")
    public Users criteria(@PathVariable Long id) {
        return newclass.findById(id);
    }
}
