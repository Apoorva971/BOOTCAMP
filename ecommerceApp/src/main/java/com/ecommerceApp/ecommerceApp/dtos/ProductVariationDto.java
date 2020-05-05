package com.ecommerceApp.ecommerceApp.dtos;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ProductVariationDto {
//    @NotNull(message = "Please Enter productId In ProductVariationDto Object")
    private Long productId;
    public String productName;
    private Long quantity;
    private Long price;
    private String primaryImageName;
    private Boolean isActive;
   private Map<String,String>metadata;

    public ProductVariationDto(Long id, String name, Long price, Long quantityAvailable, Boolean active, String imageName) {

    }

    public ProductVariationDto(Long productId, String productName, Long quantity,
                               Long price, String primaryImageName, Boolean isActive,
                               Map<String, String> metadata) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.primaryImageName = primaryImageName;
        this.isActive = isActive;
        this.metadata = metadata;
    }

    public ProductVariationDto(Long id, String name, Double price, Integer quantityAvailable, Boolean active) {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
