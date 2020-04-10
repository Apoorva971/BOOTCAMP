package com.springbootcamp.springsecurity.entites;

import javax.persistence.Entity;

@Entity
public class ProductReview extends Product{
    private Long Customer_User_Id;
    private String Review;
    private String Rating;

    public Long getCustomer_User_Id() {
        return Customer_User_Id;
    }

    public void setCustomer_User_Id(Long customer_User_Id) {
        Customer_User_Id = customer_User_Id;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public ProductReview(Long product_Id, Long seller_User_Id, String name, String category_Id) {
        super(product_Id, seller_User_Id, name, category_Id);

    }
}
