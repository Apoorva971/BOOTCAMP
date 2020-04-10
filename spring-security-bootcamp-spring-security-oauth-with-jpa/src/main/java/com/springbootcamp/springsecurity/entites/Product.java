package com.springbootcamp.springsecurity.entites;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Product_Id;

    private Long Seller_User_Id;
    private String Name;
    private String Category_Id;

    public Long getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(Long product_Id) {
        Product_Id = product_Id;
    }

    public Long getSeller_User_Id() {
        return Seller_User_Id;
    }

    public void setSeller_User_Id(Long seller_User_Id) {
        Seller_User_Id = seller_User_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(String category_Id) {
        Category_Id = category_Id;
    }

    public Product(Long product_Id, Long seller_User_Id, String name, String category_Id) {
        Product_Id = product_Id;
        Seller_User_Id = seller_User_Id;
        Name = name;
        Category_Id = category_Id;

    }
}
