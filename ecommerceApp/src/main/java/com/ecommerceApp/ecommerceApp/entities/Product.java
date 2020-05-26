package com.ecommerceApp.ecommerceApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;

    private boolean isReturnable;
    private boolean isCancelleable;
    private boolean isActive;
    private boolean isDeleted;
    @Column(name = "createdDate",nullable = false,updatable =false)
    @CreatedDate
    private Date createdDate;
    @Column(name = "modifiedDate")
    @LastModifiedDate
    private Date modifiedDate;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private Seller seller;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductVariation> variations;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews;

    {
        isActive = true;
        isCancelleable = true;
        isReturnable = true;
        isDeleted = false;
    }

    public Product() {
    }
    public Product(String name, String description, String brand, boolean isReturnable, boolean isCancelleable) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.isReturnable = isReturnable;
        this.isCancelleable = isCancelleable;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<ProductVariation> getVariations() {
        return variations;
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

    public Set<ProductVariation> getVariations(Pageable pageable) {
        return variations;
    }

    public void setVariations(Set<ProductVariation> variations) {
        this.variations = variations;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public void addVariation(ProductVariation variation) {
        if (variation != null) {
            if (variations == null)
                variations = new HashSet<>();

            variations.add(variation);
            variation.setProduct(this);
        }
    }

    public void addReview(ProductReview review) {
        if (review != null) {
            if (reviews == null)
                reviews = new ArrayList<>();

            reviews.add(review);

            review.setProduct(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", isReturnable=" + isReturnable +
                ", isCancelleable=" + isCancelleable +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", seller=" + seller +
                ", variations=" + variations +
                ", category=" + category +
                ", reviews=" + reviews +
                '}';
    }
}