package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.entities.Category;
import com.ecommerceApp.ecommerceApp.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "Shows all the category", authorizations = {@Authorization("Bearer")})
    @GetMapping(value = "/category", produces = "application/json")
    public List<CategoryDto> viewAll() {
        return categoryService.getAll();
    }

    @ApiOperation(value = "Shows the category by id", authorizations = {@Authorization("Bearer")})
    @GetMapping(value = "/category/{id}", produces = "applicayion/json")
    public CategoryDto getacategory(@PathVariable Long id, Locale locale) {
        return categoryService.getACategory(id, locale);
    }

    @ApiOperation(value = "Add a new category", authorizations = {@Authorization("Bearer")})
    @PostMapping(value = "/admin/category/add", produces = "application/json")
    public String addCategory(@RequestBody Category category, Locale locale) {
        return categoryService.addCategory(category, locale);
    }

    @ApiOperation(value = "update the category", authorizations = {@Authorization("Bearer")})
    @PutMapping(value = "/category/update/{id}", produces = "application/json")
    public String updateCategory(@PathVariable Long id, @RequestBody Category name, Locale locale) {
        return categoryService.updateCategory(id, name, locale);
    }
}
