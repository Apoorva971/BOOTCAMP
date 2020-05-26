package com.ecommerceApp.ecommerceApp.dtos;
public class CartDto {
    private String cartProductId;
    private Long customerId;
    private Integer quantity;
    private boolean is_wishlist_item;
    private Long productVariationId;

    public CartDto(){
    }
    public CartDto(String cartProductId, Integer quantity, boolean isWishList, Long customerId, Long productVariationId) {
        this.cartProductId=cartProductId;
        this.quantity=quantity;
        this.is_wishlist_item =isWishList;
        this.customerId=customerId;
        this.productVariationId=productVariationId;
    }

    public CartDto(String id, Integer quantity, boolean is_wishlist_item) {
        this.cartProductId=cartProductId;
        this.quantity=quantity;
        this.is_wishlist_item =is_wishlist_item;
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

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isIs_wishlist_item() {
        return is_wishlist_item;
    }

    public void setIs_wishlist_item(boolean is_wishlist_item) {
        this.is_wishlist_item = is_wishlist_item;
    }

    public Long getProductVariationId() {
        return productVariationId;
    }
}
