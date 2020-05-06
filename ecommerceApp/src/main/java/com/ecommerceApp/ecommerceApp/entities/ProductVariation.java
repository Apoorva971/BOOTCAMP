package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.*;
import java.util.Map;

@Entity
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantityAvailable;
    private Long price;
    private String primaryImageName;

    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "json")
    private Map<String,String>metadata;
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductVariation() {
    }

    public ProductVariation(Long quantityAvailable, Long price) {
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public ProductVariation(Long id, Long quantityAvailable, Long price, String primaryImageName, Map<String, String> metadata, Boolean isActive, Product product) {
        this.id = id;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.primaryImageName = primaryImageName;
        this.metadata = metadata;
        this.isActive = isActive;
        this.product = product;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
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

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
                "id=" + id +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                ", primaryImageName='" + primaryImageName + '\'' +
                ", metadata='" + metadata + '\'' +
                ", isActive=" + isActive +
                ", product=" + product +
                '}';
    }
}