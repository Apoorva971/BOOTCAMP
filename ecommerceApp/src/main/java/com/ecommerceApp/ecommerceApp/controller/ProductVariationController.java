package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationDto;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.services.ProductVariationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    ////////////////done
    @ApiOperation(value = "Api to view all ProductVariation of the Product",authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/seller/productvariation/{id}",produces = "application/json")
    public ProductVariation getProductVariation(@PathVariable Long id) {
        return productVariationService.getProductVariation(id);
    }
    //////////////////////not working
    @ApiOperation(value = "Api to add a new ProductVariation of the Product",authorizations = {@Authorization(value = "Bearer")})

    @PostMapping(value = "/seller/productVariation",produces = "application/json")
    public String addProductVariation(@RequestBody ProductVariationDto productVariationDto) {
        productVariationService.addProductVariation(productVariationDto);
        return "saved";
    }
    ///////////////not working
    @ApiOperation(value = "Api to Update the ProductVariation of the Product",authorizations = {@Authorization(value = "Bearer")})

    @PutMapping(value = "/seller/productVariation",produces = "application/json")
    public String updateNewProductVariation(@RequestParam("productVariationId") Long productVariationId,
                                            @RequestBody ProductVariationDto productVariationDto, Locale locale)
            throws IOException {
        return productVariationService.updateProductVariation(productVariationId, productVariationDto, locale);
    }
    /////////////////////done
    @ApiOperation(value = "Api to view all the ProductVariation",authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/seller/productVariations",produces = "application/json")
    public List<ProductVariationDto> getProductVariation(@RequestBody(required = false)
                                                                 PagingAndSortingDto pagingAndSortingDto) {
        return productVariationService.getAllProductVariationOfSeller(pagingAndSortingDto);

    }
}