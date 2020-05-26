package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CartDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Cart;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.services.CartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class CartController {
        @Autowired
        CartService cartService;
        //============Api To view A Product Of Cart============//

        @ApiOperation(value = "Api to display a single cart product", authorizations = {@Authorization(value = "Bearer")})
        @GetMapping(value = "/cart/{id}", produces = "application/json")
        public CartDto getSingleCartProduct(@PathVariable String id){
                return cartService.getSingleCartProduct(id);
        }

        //============Api To Add Product To Cart============//

        @ApiOperation(value = "Api to add the product to the cart", authorizations = {@Authorization(value = "Bearer")})
        @PostMapping(value = "/cart", produces = "application/json")
        public ProductDto addToCart(@RequestBody CartDto cartDto,@ApiIgnore WebRequest webRequest){
                return cartService.addProductToCart(cartDto);
        }
        //============Api To View All Product Of Cart============//

        @ApiOperation(value = "Api to display  All cart product", authorizations = {@Authorization(value = "Bearer")})
        @GetMapping(value = "/carts", produces = "application/json")
        public List<CartDto> getCartObjects(){
                return cartService.getCartProducts();
        }

        //============Api To Update Product Of Cart============//

        @ApiOperation(value = "Api to update the product of the cart", authorizations = {@Authorization(value = "Bearer")})
        @PutMapping(value = "/cart/{id}",produces = "application/json")
        public ReturnJson updateCartProduct(@RequestParam("id")String id, @Valid @RequestBody CartDto cartDto, @ApiIgnore Locale locale){
                return cartService.updateCartProduct(id,cartDto,locale);
        }

        //============Api To Delete Product Of Cart============//

        @ApiOperation(value = "Api to update the product of the cart", authorizations = {@Authorization(value = "Bearer")})
        @DeleteMapping(value = "/cart/{id}",produces = "application/json")
        public ReturnJson deleteCartProduct(@RequestParam("id") String id,@ApiIgnore Locale locale){
                return cartService.deleteCartProduct(id,locale);
        }
}
