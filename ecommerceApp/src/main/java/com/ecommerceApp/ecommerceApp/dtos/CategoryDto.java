package com.ecommerceApp.ecommerceApp.dtos;

import javax.validation.constraints.NotEmpty;
//
//public class CategoryDto {
// private Long id;
// private String name;
//
//    public CategoryDto(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}

public class CategoryDto {

    private Long id;
    @NotEmpty
    private String name;
    private Long parentId;

    public CategoryDto(Long id, String name, Long parentId) {
        this.id =id;
        this.name =name;
        this.parentId=parentId;
    }
    public CategoryDto(Long id, String name) {
        this.id =id;
        this.name =name;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
