package com.ecommerceApp.ecommerceApp.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;

import javax.persistence.*;
//@Entity
@Document(collection = "Cart")
public class Cart {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

//        @OneToOne
//    @JoinColumn(name = "CUSTOMER_USER_ID")
//    private Customer customer;
    private Long customerId;
    private Long productVariationId;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "IS_WISHLIST_ITEM")
    private boolean is_wishlist_item;
//
//    @OneToOne
//    @JoinColumn(name = "PRODUCT_VARIATION_ID")
//    private ProductVariation productVariation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

//    public boolean isIs_wishlist_item() {
//        return is_wishlist_item;
//    }
//
//    public void setIs_wishlist_item(boolean is_wishlist_item) {
//        is_wishlist_item = is_wishlist_item;
//    }

    public boolean isIs_wishlist_item() {
        return is_wishlist_item;
    }

    public void setIs_wishlist_item(boolean is_wishlist_item) {
        this.is_wishlist_item = is_wishlist_item;
    }


//    public ProductVariation getProductVariation() {
//        return productVariation;
//    }
//
//    public void setProductVariation(ProductVariation productVariation) {
//        this.productVariation = productVariation;
//    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }
}

