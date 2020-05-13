package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.dtos.FilterCategoryDto;
import com.ecommerceApp.ecommerceApp.entities.Category;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
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
    @GetMapping(value = "/admin/category/{id}", produces = "applicayion/json")
    public CategoryDto getacategory(@PathVariable Long id, @ApiIgnore Locale locale) {
        return categoryService.getACategory(id, locale);
    }

    @ApiOperation(value = "Add a new category", authorizations = {@Authorization("Bearer")})
    @PostMapping(value = "/admin/category", produces = "application/json")
    public ReturnJson addCategory(@RequestBody Category category,@ApiIgnore Locale locale) {
        return categoryService.addCategory(category, locale);
    }

    @ApiOperation(value = "update the category", authorizations = {@Authorization("Bearer")})
    @PutMapping(value = "/admin/category/update/{id}", produces = "application/json")
    public ReturnJson updateCategory(@PathVariable Long id, @RequestBody Category name, @ApiIgnore Locale locale) {
        return categoryService.updateCategory(id, name, locale);
    }
    ///////////////////////////////
    @ApiOperation(value = "View filter category values",authorizations = {@Authorization("Bearer")})
    @GetMapping("/customer/category/filter/{id}")
    public FilterCategoryDto filterCategoryByIdByCustomer(@Valid @PathVariable Long id,@ApiIgnore Locale locale ){
        return categoryService.filterCategoryByCustomer(id, locale);
    }
}
