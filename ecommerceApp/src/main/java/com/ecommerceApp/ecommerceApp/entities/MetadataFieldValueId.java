package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MetadataFieldValueId implements Serializable {
    private Long categoryId;
    private Long categoryMetadataFieldId;

    public MetadataFieldValueId(Long categoryId, Long categoryMetadataFieldId) {
        this.categoryId = categoryId;
        this.categoryMetadataFieldId = categoryMetadataFieldId;
    }

    public MetadataFieldValueId(){

    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryMetadataFieldId() {
        return categoryMetadataFieldId;
    }

    public void setCategoryMetadataFieldId(Long categoryMetadataFieldId) {
        this.categoryMetadataFieldId = categoryMetadataFieldId;
    }

    @Override
    public String toString() {
        return "MetadataFieldValueId{" +
                "categoryId=" + categoryId +
                ", categoryMetadataFieldId=" + categoryMetadataFieldId +
                '}';
    }
}
