package com.ecommerceApp.ecommerceApp.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;
@Document(collection = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Long customerId;
    private Integer ordered_quantity;
    private boolean is_wishlist_item;
    List<ProductVariation> productVariationList;
//    List<Customer>customers;
    public List<ProductVariation> getProductVariationList() {
        return productVariationList;
    }

    public void setProductVariationList(List<ProductVariation> productVariationList) {
        this.productVariationList = productVariationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

//    public List<Customer> getCustomers() {
//        return customers;
//    }
//
//    public void setCustomers(List<Customer> customers) {
//        this.customers = customers;
//    }
}

