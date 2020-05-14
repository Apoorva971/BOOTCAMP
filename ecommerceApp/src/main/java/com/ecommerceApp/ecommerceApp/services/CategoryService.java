package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.dtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.dtos.FilterCategoryDto;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.CategoryAlreadyRegistered;
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

import java.util.*;

@Component
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryMetaDataFieldValuesRepository categoryMetaDataFieldValuesRepository;
    @Autowired
    ProductVariationRepository productVariationRepository;

    public List<CategoryDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll(PageRequest.of(0, 5,
                Sort.Direction.ASC, "id"));
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(categoryDto -> categoryDtoList.add(new CategoryDto(categoryDto.getId(),
                categoryDto.getName())));
        return categoryDtoList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public CategoryDto getACategory(Long id, Locale locale) {
        if (!categoryRepository.findById(id).isPresent()) {
            throw new InvalidDetailException(messageSource.getMessage("category.invalidId.message", null, locale));
        }
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());
        return categoryDto;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    public ReturnJson addCategory(String categoryname,Long id, Locale locale) {
        Category category1 = categoryRepository.findByName(categoryname);
        if (category1 != null) {
            throw new InvalidDetailException(messageSource.getMessage("category.alreadyPresent.message", null, locale));
        }
        try {
            if (id != null) {
                Category parentCategory = categoryRepository.findById(id).get();
                Category newCategory = new Category();
                newCategory.setName(categoryname);
                newCategory.setParentCategory(parentCategory.getParentCategory());
                categoryRepository.save(newCategory);
            } else {
                Category newCategory = new Category();
                newCategory.setName(categoryname);
                categoryRepository.save(newCategory);
            }
        } catch (Exception ex) {
            throw new CategoryAlreadyRegistered(messageSource.getMessage("category.alreadyRegisterd.message", null, locale));
        }
        return new ReturnJson(messageSource.getMessage("category.added.message", null, locale));
    }

//    ///////////////////////////////////////////////////////////////////////////////////////////////
    public ReturnJson updateCategory(Long id, String name, Locale locale) {
        Optional<Category> productCategory = categoryRepository.findById(id);
        try {
            Category category = productCategory.get();
//            category.setName(name.getName());
            category.setName(name);
            categoryRepository.save(category);
        } catch (Exception ex) {
            throw new ProductNotFoundException(messageSource.getMessage("category.invalidId.message", null, locale));
        }
        return new ReturnJson(messageSource.getMessage("category.update.message", null, locale));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public FilterCategoryDto filterCategoryByCustomer(Long categoryId, Locale locale) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        if (productList.isEmpty())
            throw new ProductNotFoundException(messageSource.getMessage("category.invalidId.message", null, locale));
        FilterCategoryDto filterCategoryDto = new FilterCategoryDto();
        List<CategoryMetadataFieldValues> categoryFieldValuesList = new ArrayList<>();
        Iterator<CategoryMetadataFieldValues> categoryFieldValuesIterator = categoryMetaDataFieldValuesRepository.findAll().iterator();
        while (categoryFieldValuesIterator.hasNext()) {
            CategoryMetadataFieldValues currentCategoryFieldValues = categoryFieldValuesIterator.next();
            if (currentCategoryFieldValues.getId().getCategoryId() == categoryId) {
                categoryFieldValuesList.add(currentCategoryFieldValues);
            }
        }
        Integer max = Integer.MIN_VALUE;
        Integer min = Integer.MAX_VALUE;
        Set<String> brandsList = new HashSet<>();
        Iterator<Product> productIterator = productRepository.findAllByCategoryId(categoryId).iterator();
        while (productIterator.hasNext()) {
            Product currentProduct = productIterator.next();
            if (currentProduct.getCategory().getId() == categoryId) {
                brandsList.add(currentProduct.getBrand());
                Iterator<ProductVariation> productVariantIterator = productVariationRepository.findByProductId(currentProduct.getId()).iterator();
                while (productVariantIterator.hasNext()) {
                    ProductVariation currentVariant = productVariantIterator.next();
                    if (currentVariant.getPrice() <= min)
                        min = currentVariant.getPrice().intValue();
                    if (currentVariant.getPrice() >= max)
                        max = currentVariant.getPrice().intValue();
                }
            }
        }
        filterCategoryDto.setCategoryFieldValues(categoryFieldValuesList);
        if (max > 0)
            filterCategoryDto.setMaxPrice(max);
        if (min < Integer.MAX_VALUE)
            filterCategoryDto.setMinPrice(min);
        filterCategoryDto.setBrandsList(brandsList);
        return filterCategoryDto;
    }
}


