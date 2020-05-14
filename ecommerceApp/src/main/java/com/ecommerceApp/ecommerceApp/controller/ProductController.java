package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //////////done
    @ApiOperation(value = "Api to add a new Product", authorizations = {@Authorization(value = "Bearer")})

    @PostMapping(value = "/seller/product", produces = "application/json")
    public ReturnJson addProduct(@RequestBody Product product, @ApiIgnore Locale locale) {
        return productService.addProduct(product, locale);
    }

    /////////////////done
    @ApiOperation(value = "Api to view all the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/Products", produces = "application/json")
    public List<ProductDto> viewAllProductAsSeller(@RequestParam(required = false) PagingAndSortingDto pagingAndSortingDto) {
        return productService.viewAllProduct(pagingAndSortingDto);
    }

    /////////////done
    @ApiOperation(value = "Api to delete the Product", authorizations = {@Authorization(value = "Bearer")})

    @DeleteMapping(value = "/seller/product/{productId}", produces = "application/json")
    public ReturnJson deleteProductAsSeller(@PathVariable Long productId, @ApiIgnore Locale locale) {
        return productService.deleteProduct(productId, locale);
    }

    /////////done
    @ApiOperation(value = "Api to Update the Product", authorizations = {@Authorization(value = "Bearer")})

    @PostMapping(value = "/seller/product/{productId}", produces = "application/json")
    public ReturnJson updateProductAsSeller(@PathVariable Long productId, @RequestBody ProductDto productDto, @ApiIgnore Locale locale) {
        return productService.updateProduct(productId, productDto, locale);

    }

    ///////////////////done
    @ApiOperation(value = "Api to Activate the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/admin/product/activate/{productId}", produces = "application/json")
    public ReturnJson activateProduct(@PathVariable Long productId, @ApiIgnore Locale locale) {
        return productService.activateProduct(productId, locale);
    }

    ///////////////////done
    @ApiOperation(value = "Api to De-Activate the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping("/admin/product/deactivate/{productId}")
    public ReturnJson deactivateProduct(@PathVariable Long productId, @ApiIgnore Locale locale) {
        return productService.deactivateProduct(productId, locale);
    }

    ///////////done
    @ApiOperation(value = "Api to view the Product Aa a Customer", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/Product/{productId}", produces = "application/json")
    public Optional<Product> viewProduct(@PathVariable Long productId) {
        return productService.viewAProduct(productId);
    }

    ////////////////done
    @ApiOperation(value = "Api to view all the Category of the Product As a Customer", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/customer/products/{categoryId}", produces = "application/json")
    public List<Product> viewAllProduct(@PathVariable Long categoryId) {
        return productService.viewAllProductAsCustomer(categoryId);
    }
}