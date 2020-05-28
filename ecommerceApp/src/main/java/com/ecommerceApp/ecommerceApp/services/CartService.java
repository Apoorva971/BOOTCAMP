package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CartRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
import com.ecommerceApp.ecommerceApp.dtos.CartDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Cart;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    public CartDto getSingleCartProduct(String  id) {
        if (!cartRepository.findById(id).isPresent()) {
            throw new InvalidDetailException("Invalid Cart Product Id");
        }
        Cart cart = cartRepository.findById(id).get();
            CartDto cartDto= new CartDto(cart.getId(), cart.getQuantity(), cart.isIs_wishlist_item(), cart.getCustomerId(), cart.getProductVariationId());
//                cart.getCustomer().getId(),cart.getProductVariation().getId());
//            Product product = productVariationRepository.findById(cartDto.getProductVariationId()).get().getProduct();
            return cartDto;
    }
    public ProductDto addProductToCart(CartDto cartDto){
        Cart cart = new Cart();
//        cart.setCustomerId(getLoggedInCustomer().getId());
//        cart.setProductVariationId(productVariationRepository.findById(cartDto.getProductVariationId()).get());
            cart.setCustomerId(cartDto.getCustomerId());
            cart.setProductVariationId(cartDto.getProductVariationId());
            cart.setQuantity(cartDto.getQuantity());
            cart.setIs_wishlist_item(cartDto.isIs_wishlist_item());
            cartRepository.save(cart);
            Product product = productVariationRepository.findById(cartDto.getProductVariationId()).get().getProduct();
            ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getCategory().getId(),
                    product.getCategory().getName(), product.getDescription(), product.isCancelleable(), product.isReturnable(),
                    product.getBrand(), product.isActive());
            return productDto;
        }
    public List<CartDto> getCartProducts(){
        List<Cart> carts= cartRepository.findAll();
        List<CartDto> cartDtoList = new ArrayList<>();
        carts.forEach(carts1 -> cartDtoList.add(new CartDto(carts1.getId(),carts1.getQuantity(),carts1.isIs_wishlist_item(),
                carts1.getCustomerId(),carts1.getProductVariationId())));
        return cartDtoList;
    }
    public ReturnJson updateCartProduct(String id, CartDto cartDto, Locale locale){
        if (!cartRepository.findById(id).isPresent()){
            throw new InvalidDetailException("Invalid Cart Product Id");
        }
        Cart cart = cartRepository.findById(id).get();
        cart.setQuantity(cartDto.getQuantity());
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
}
