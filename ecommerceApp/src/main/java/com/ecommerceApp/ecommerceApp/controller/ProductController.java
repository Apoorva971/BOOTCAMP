package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "product added successfully";
    }

    //////////done
    @ApiOperation(value = "Api to view the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/seller/product/{productId}", produces = "application/json")
    public Optional<Product> viewProductAsSeller(@PathVariable Long productId) {
        Optional<Product> product = productService.viewProduct(productId);
        return product;
    }

    /////////////////done
    @ApiOperation(value = "Api to view all the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/seller/Products", produces = "application/json")
    public List<Product> viewAllProductAsSeller() {
        return productService.viewAllProductAsSeller();
    }

    /////////////done
    @ApiOperation(value = "Api to delete the Product", authorizations = {@Authorization(value = "Bearer")})

    @DeleteMapping(value = "/seller/product/{productId}", produces = "application/json")
    public String deleteProductAsSeller(@PathVariable Long productId, Locale locale) {
        productService.deleteProduct(productId, locale);
        return "product deleted successfully";
    }

    /////////done
    @ApiOperation(value = "Api to Update the Product", authorizations = {@Authorization(value = "Bearer")})

    @PostMapping(value = "/seller/product/{productId}", produces = "application/json")
    public String updateProductAsSeller(@PathVariable Long productId, @RequestBody ProductDto productDto, Locale locale) {
        productService.updateProduct(productId, productDto, locale);
        return "product updated successfully";
    }

    ///////////////////done
    @ApiOperation(value = "Api to Activate the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/admin/product/activate/{productId}", produces = "application/json")
    public String activateProduct(@PathVariable Long productId, Locale locale) {
        return productService.activateProduct(productId, locale);
    }

    ///////////////////done
    @ApiOperation(value = "Api to De-Activate the Product", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping("/admin/product/deactivate/{productId}")
    public String deactivateProduct(@PathVariable Long productId, Locale locale) {
        return productService.deactivateProduct(productId, locale);
    }

    ///////////done
    @ApiOperation(value = "Api to view the Product Aa a Customer", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/customer/product/{productId}", produces = "application/json")
    public Optional<Product> viewProduct(@PathVariable Long productId) {
        return productService.viewProductAsCustomer(productId);
    }

    ////////////////done
    @ApiOperation(value = "Api to view all the Category of the Product As a Customer", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/products/{categoryId}", produces = "application/json")
    public List<Product> viewAllProduct(@PathVariable Long categoryId) {
        return productService.viewAllProductAsCustomer(categoryId);
    }

    //////////////done
    @ApiOperation(value = "Api to view the Product As a Admin", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/admin/product/{productId}", produces = "application/json")
    public Optional<Product> viewProductAsAdmin(@PathVariable Long productId) {
        return productService.viewAProductAsAdmin(productId);
    }

    //////////////////doen
    @ApiOperation(value = "Api to view all the Category As a Admin", authorizations = {@Authorization(value = "Bearer")})

    @GetMapping(value = "/admin/products/{categoryId}", produces = "application/json")
    public List<Product> viewAllProductAsAdmin(@PathVariable Long categoryId) {
        return productService.viewAllProductsAsAdmin(categoryId);
    }

}