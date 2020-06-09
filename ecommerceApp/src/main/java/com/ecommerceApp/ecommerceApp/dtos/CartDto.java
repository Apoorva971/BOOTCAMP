package com.ecommerceApp.ecommerceApp.dtos;
import com.ecommerceApp.ecommerceApp.entities.ProductVariation;

import java.util.List;
public class CartDto {
    private String cartProductId;
    private Long customerId;
    private Integer ordered_quantity;
    private boolean is_wishlist_item;
    List<ProductVariation>productVariations;
    public CartDto(){
    }
    public CartDto(String id, Integer ordered_quantity, boolean is_wishlist_item, Long customerId, List<ProductVariation> productVariationList) {
        this.cartProductId = id;
        this.ordered_quantity = ordered_quantity;
        this.is_wishlist_item = is_wishlist_item;
        this.customerId=customerId;
        this.productVariations = productVariationList;
    }
    public List<ProductVariation> getProductVariations() {
        return productVariations;
    }
    public void setProductVariations(List<ProductVariation> productVariations) {
        this.productVariations = productVariations;
    }
    public String getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(String cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Integer getOrdered_quantity() {
        return ordered_quantity;
    }

    public void setOrdered_quantity(Integer ordered_quantity) {
        this.ordered_quantity = ordered_quantity;
    }

    public boolean isIs_wishlist_item() {
        return is_wishlist_item;
    }

    public void setIs_wishlist_item(boolean is_wishlist_item) {
        this.is_wishlist_item = is_wishlist_item;
    }

}
