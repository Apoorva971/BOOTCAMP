package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationDto;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.services.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class ProductVariationController {
    @Autowired
    ProductVariationService productVariationService;

    @GetMapping("/productvariation/{id}")
    public ProductVariation getProductVariation(@PathVariable Long id) {
        return productVariationService.getProductVariation(id);
    }

    @PostMapping("/seller/productVariation/add")
    public String addProductVariation(@RequestBody ProductVariationDto productVariationDto) {
        productVariationService.addProductVariation(productVariationDto);
        return "saved";
    }

    @PutMapping("/seller/update/productVariation")
    public String updateNewProductVariation(@RequestParam("productVariationId") Long productVariationId,
                                            ProductVariationDto productVariationDto, Locale locale)
            throws IOException {
        return productVariationService.updateProductVariation(productVariationId, productVariationDto, locale);
    }

    @GetMapping("/seller/all/productVariation")
    public List<ProductVariationDto> getProductVariation(@RequestBody(required = false)
                                                                 PagingAndSortingDto pagingAndSortingDto) {
        return productVariationService.getAllProductVariationOfSeller(pagingAndSortingDto);

    }
}