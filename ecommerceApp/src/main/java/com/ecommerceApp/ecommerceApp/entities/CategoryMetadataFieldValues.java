package com.ecommerceApp.ecommerceApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CategoryMetadataFieldValues implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private MetadataFieldValueId id=new MetadataFieldValueId();
    @ManyToOne
    @JsonIgnore
    @MapsId("categoryMetadataFieldId")
    @JoinColumn(name = "categoryMetadDataFieldId")
    private CategoryMetaDataField categoryMetaDataField;
    @ManyToOne
    @JsonIgnore
    @MapsId("categoryId")
    @JoinColumn(name = "categoryId")
    private Category category;

    private String metadataValues;

    public CategoryMetadataFieldValues(String metadataValues){

    }
    public CategoryMetadataFieldValues(CategoryMetaDataField categoryMetaDataField,
                                       Category category, String metadataValues) {
        this.categoryMetaDataField = categoryMetaDataField;
        this.category = category;
        this.metadataValues = metadataValues;
    }

    public MetadataFieldValueId getId() {
        return id;
    }
    public void setCategoryMetadataField(CategoryMetaDataField categoryMetadataField) {
        this.categoryMetaDataField = categoryMetadataField;
    }
public CategoryMetadataFieldValues(){

}
    public void setId(MetadataFieldValueId id) {
        this.id = id;
    }

    public CategoryMetaDataField getCategoryMetaDataField() {
        return categoryMetaDataField;
    }

    public void setCategoryMetaDataField(CategoryMetaDataField categoryMetaDataField) {
        this.categoryMetaDataField = categoryMetaDataField;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMetadataValues() {
        return metadataValues;
    }

    public void setMetadataValues(String metadataValues) {
        this.metadataValues = metadataValues;
    }
}
