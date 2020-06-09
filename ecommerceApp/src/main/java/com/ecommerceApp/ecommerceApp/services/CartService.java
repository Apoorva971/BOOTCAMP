package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CartRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.dtos.CartDto;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    MessageSource messageSource;

    public Customer getLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Customer customer = customerRepository.findByEmail(username);
        return customer;
    }

    public CartDto getSingleCartProduct(String  id) {
        if (!cartRepository.findById(id).isPresent()) {
            throw new InvalidDetailException("Invalid Cart Product Id");
        }
        Cart cart = cartRepository.findById(id).get();
        CartDto cartDto= new CartDto(cart.getId(), cart.getOrdered_quantity(), cart.isIs_wishlist_item(),
                cart.getCustomerId(),cart.getProductVariationList());
            return cartDto;
    }
    public ReturnJson addProductToCart(CartDto cartDto,Locale locale){
        Cart cart = new Cart();
        cart.setCustomerId(getLoggedInCustomer().getId());
            cart.setProductVariationList(cartDto.getProductVariations());
            cart.setOrdered_quantity(cartDto.getOrdered_quantity());
            cart.setIs_wishlist_item(cartDto.isIs_wishlist_item());
            cartRepository.save(cart);
        return new ReturnJson(messageSource.getMessage("product.added.in.cart.message",null,locale));
        }

    public List<CartDto> getCartProducts(){
        List<Cart> carts= cartRepository.findAllByCustomerid(getLoggedInCustomer().getId());
        List<CartDto> cartDtoList = new ArrayList<>();
        carts.forEach(carts1 -> cartDtoList.add(new CartDto(carts1.getId(),carts1.getOrdered_quantity(),carts1.isIs_wishlist_item(),
                carts1.getCustomerId(),carts1.getProductVariationList())));
        return cartDtoList;
    }

    public ReturnJson updateCartProduct(String id, CartDto cartDto, Locale locale){
        if (!cartRepository.findById(id).isPresent()){
            throw new InvalidDetailException("Invalid Cart Product Id");
        }
        Cart cart = cartRepository.findById(id).get();
        cart.setOrdered_quantity(cartDto.getOrdered_quantity());
        cart.setIs_wishlist_item(cartDto.isIs_wishlist_item());
         cartRepository.save(cart);
        return new ReturnJson(messageSource.getMessage("cart.updated.message", null, locale));

    }

    public ReturnJson deleteCartProduct(String id,Locale locale){
        if (!cartRepository.findById(id).isPresent()){
            throw new InvalidDetailException("Invalid Cart Product Id");
        }
        cartRepository.deleteById(id);
        return new ReturnJson(messageSource.getMessage("cart.deleted.message",null,locale));
    }
    ////////////////////////////////
}
