package com.springbootcamp.springsecurity.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductVariation extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Quantity_Available;
    private double Price;

    public ProductVariation(Long product_Id, Long seller_User_Id, String name, String category_Id) {
        super(product_Id, seller_User_Id, name, category_Id);
    }

    public Integer getQuantity_Available() {
        return Quantity_Available;
    }

    public void setQuantity_Available(Integer quantity_Available) {
        Quantity_Available = quantity_Available;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
