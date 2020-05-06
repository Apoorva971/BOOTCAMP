package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.entities.Product;
import java.util.Map;

public class ProductVariationDto {
    private Long productId;
    private Product product;

    private Long quantity;
    private Long price;
    private String primaryImageName;
    private Boolean isActive;
   private Map<String,String>metadata;

    public ProductVariationDto(Long id, Product product, Map<String, String> metadata, Long price, Long quantityAvailable, Boolean active, String primaryImageName) {
        this.productId = id;
        this.quantity = quantityAvailable;
        this.price = price;
        this.primaryImageName = primaryImageName;
        this.metadata = metadata;
        this.isActive = active;
        this.product=product;
    }
    public ProductVariationDto(){

    }

    public ProductVariationDto(Long productId, Product productName, Long quantity,
                               Long price, String primaryImageName, Boolean isActive,
                               Map<String, String> metadata) {
        this.productId = productId;
        this.product = productName;
        this.quantity = quantity;
        this.price = price;
        this.primaryImageName = primaryImageName;
        this.isActive = isActive;
        this.metadata = metadata;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
