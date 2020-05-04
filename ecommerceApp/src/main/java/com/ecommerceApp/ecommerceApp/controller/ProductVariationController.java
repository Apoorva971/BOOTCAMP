package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.ProductVariationDto;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.services.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductVariationController {
    @Autowired
    ProductVariationService productVariationService;

    @GetMapping("/productvariation/{id}")
    public ProductVariation getProductVariation(@PathVariable Long id) {
        return productVariationService.getProductVariation(id);
    }
    @PostMapping("/seller/product/variation/add")
    public String addProductVariation(@RequestBody ProductVariationDto productVariationDto){
        productVariationService.addProductVariation(productVariationDto);
        return "saved";
    }
}
