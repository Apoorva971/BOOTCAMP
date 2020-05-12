package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.dtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.entities.Category;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.exceptions.CategoryAlreadyRegistered;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidCategoryOrFieldIdException;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MessageSource messageSource;

    public List<CategoryDto> getAll() {
      List<Category>categoryList = categoryRepository.findAll(PageRequest.of(0,5,
              Sort.Direction.ASC,"id"));
      List<CategoryDto> categoryDtoList = new ArrayList<>();
      categoryList.forEach(categoryDto -> categoryDtoList.add(new CategoryDto(categoryDto.getId(),
              categoryDto.getName())));
      return categoryDtoList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public CategoryDto getACategory(Long id,Locale locale) {
        if (!categoryRepository.findById(id).isPresent()) {
            throw new InvalidDetailException(messageSource.getMessage("category.invalidId.message",null,locale));
        }
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());
        return categoryDto;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    public ReturnJson addCategory(Category category, Locale locale) {
        Category category1 = categoryRepository.findByName(category.getName());
        if(category1!=null){
            throw new InvalidDetailException(messageSource.getMessage("category.alreadyPresent.message",null,locale));
        }
        try {
            if(category.getId() !=null){
                Category parentCategory = categoryRepository.findById(category.getId()).get();
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                newCategory.setParentCategory(parentCategory.getParentCategory());
            categoryRepository.save(newCategory);}
            else{
                Category newCategory = new Category();
                newCategory.setName(category.getName());
                categoryRepository.save(newCategory);
            }
        } catch (Exception ex) {
            throw new CategoryAlreadyRegistered(messageSource.getMessage("category.alreadyRegisterd.message",null,locale));
        }
        return new ReturnJson( messageSource.getMessage("category.added.message",null,locale));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public ReturnJson updateCategory(Long id, Category name,Locale locale) {
        Optional<Category> productCategory = categoryRepository.findById(id);
        try {
            Category category = productCategory.get();
            category.setName(name.getName());
            categoryRepository.save(category);
        } catch (Exception ex) {
            throw new ProductNotFoundException(messageSource.getMessage("category.invalidId.message",null,locale));
        }
        return new ReturnJson( messageSource.getMessage("category.update.message",null,locale));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
}


