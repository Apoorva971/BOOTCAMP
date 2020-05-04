package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.services.ProductService;
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
    @PostMapping("/seller/product/add")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "product added successfully";
    }

    //////////done
    @GetMapping("/seller/product/view/{productId}")
    public Optional<Product> viewProductAsSeller(@PathVariable Long productId) {
        Optional<Product> product = productService.viewProduct(productId);
        return product;
    }

    /////////////////done
    @GetMapping("/seller/viewAll/Product")
    public List<Product> viewAllProductAsSeller() {
        return productService.viewAllProductAsSeller();
    }

    /////////////donedone
    @DeleteMapping("/seller/product/delete/{productId}")
    public String deleteProductAsSeller(@PathVariable Long productId, Locale locale) {
        productService.deleteProduct(productId,locale);
        return "product deleted successfully";
    }

    /////////woking but category not chnaging
    @PostMapping("/seller/product/update/{productId}")
    public String updateProductAsSeller(@PathVariable Long productId, @RequestBody ProductDto productDto,Locale locale) {
        productService.updateProduct(productId, productDto,locale);
        return "product updated successfully";
    }
///////////////////done
    @GetMapping("/admin/product/activate/{productId}")
    public String activateProduct(@PathVariable Long productId,Locale locale) {
        return productService.activateProduct(productId,locale);
    }
///////////////////done
    @GetMapping("/admin/product/deactivate/{productId}")
    public String deactivateProduct(@PathVariable Long productId,Locale locale) {
        return productService.deactivateProduct(productId,locale);
    }
}