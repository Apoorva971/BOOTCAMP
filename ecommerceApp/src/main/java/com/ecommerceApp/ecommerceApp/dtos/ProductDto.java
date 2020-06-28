package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.entities.Seller;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
//changes .............................................................................................
public class ProductDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private  String categoryName;
    private Long categoryId;
    private boolean isReturnable;
    private boolean isCancelleable;
    private boolean isActive;
    private boolean isDeleted;
    private Seller seller;//new

    public ProductDto(Long id, String name, String description, String brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.isReturnable = isReturnable;
        this.isCancelleable = isCancelleable;
    }


    public ProductDto(Long productId, String productName, Long categoryId, String categoryName, String description,
                      boolean cancellable, boolean returnable, String brand,boolean isActive) {
        this.id=productId;
        this.name=productName;
        this.categoryId=categoryId;
        this.categoryName=categoryName;
        this.description=description;
        this.isCancelleable=cancellable;
        this.isReturnable=returnable;
        this.brand=brand;
        this.isActive=isActive;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public boolean isCancelleable() {
        return isCancelleable;
    }

    public void setCancelleable(boolean cancelleable) {
        isCancelleable = cancelleable;
    }

    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
