package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.entities.Category;
import com.ecommerceApp.ecommerceApp.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "Shows all the category")
    @GetMapping("/category")
    public List<CategoryDto> viewAll(){
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public CategoryDto getacategory(@PathVariable Long id,Locale locale){
        return categoryService.getACategory(id,locale);
    }

    @PostMapping("/admin/category/add")
    public String addCategory(@RequestBody Category category,Locale locale){
        return categoryService.addCategory(category,locale);
    }

    @PutMapping("/category/update/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody Category name, Locale locale){
        return categoryService.updateCategory(id,name,locale);
    }
}
