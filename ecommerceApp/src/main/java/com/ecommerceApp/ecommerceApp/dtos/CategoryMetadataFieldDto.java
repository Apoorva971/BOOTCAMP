package com.ecommerceApp.ecommerceApp.dtos;

public class CategoryMetadataFieldDto {
    private Long Id;
    private String name;

    public CategoryMetadataFieldDto() {
    }

    public CategoryMetadataFieldDto(Long id, String name) {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
