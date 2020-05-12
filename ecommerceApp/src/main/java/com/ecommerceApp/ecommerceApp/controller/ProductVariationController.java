package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.services.ProductVariationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    ////////////////////// working
    @ApiOperation(value = "Api to add a new ProductVariation of the Product",authorizations = {@Authorization(value = "Bearer")})

    @PostMapping(value = "/seller/productVariation",produces = "application/json")
    public ReturnJson addProductVariation(@RequestBody ProductVariationDto productVariationDto,Locale locale) {
       return productVariationService.addProductVariation(productVariationDto,locale);
    }
    /////////////// working
    @ApiOperation(value = "Api to Update the ProductVariation of the Product",authorizations = {@Authorization(value = "Bearer")})

    @PutMapping(value = "/seller/productVariation",produces = "application/json")
    public ReturnJson updateNewProductVariation(@RequestParam("productVariationId") Long productVariationId,
                                                @RequestBody ProductVariationDto productVariationDto, Locale locale)
            throws IOException {
        return productVariationService.updateProductVariation(productVariationId, productVariationDto, locale);
    }
    /////////////////////done
    @ApiOperation(value = "Api to view all the ProductVariation",authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/seller/productVariations",produces = "application/json")
    public List<ProductVariationDto> getProductVariation(@RequestParam(required = false)
                                                                 PagingAndSortingDto pagingAndSortingDto) {
        return productVariationService.getAllProductVariationOfSeller(pagingAndSortingDto);

    }
    /////////////////working
    @ApiOperation(value = "Api to view similar productVariation of the Product",authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(value = "/customer/product/similar/{productId}",produces = "application/json")
    public List<Product> similarProductVariation(@PathVariable Long productId, @RequestParam(required = false) PagingAndSortingDto pagingAndSortingDto,Locale locale){
        return productVariationService.similarProductVariation(productId,pagingAndSortingDto,locale);
    }
}