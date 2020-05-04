package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryMetaDataFieldRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryMetaDataFieldValuesRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.dtos.CategoryMetadataFieldDto;
import com.ecommerceApp.ecommerceApp.entities.Category;
import com.ecommerceApp.ecommerceApp.entities.CategoryMetaDataField;
import com.ecommerceApp.ecommerceApp.entities.CategoryMetadataFieldValues;
import com.ecommerceApp.ecommerceApp.entities.MetadataFieldValueId;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidCategoryOrFieldIdException;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class MetaDataFieldService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMetaDataFieldRepository categoryMetaDataFieldRepository;
    @Autowired
    CategoryMetaDataFieldValuesRepository categoryMetaDataFieldValuesRepository;
    @Autowired
    MessageSource messageSource;

    //////////////////////this function add a new field in the metadata
    public String addField(CategoryMetaDataField categoryMetaDataField, Locale locale) {
        if (categoryMetaDataFieldRepository.findByName(categoryMetaDataField.getName()) != null) {
            throw new InvalidDetailException("MetaDataField Already Exists so enter Unique Name");
        }
        try {
            CategoryMetaDataField categoryMetadataField = new CategoryMetaDataField(categoryMetaDataField.getName());
            categoryMetaDataFieldRepository.save(categoryMetadataField);
        } catch (Exception ex) {
            throw new InvalidCategoryOrFieldIdException("Field name already exists");
        }
        return messageSource.getMessage("field.added.message",null,locale);
    }

    ///////////////////////////////////////////done/////////////////////////////////////////////////
    public String addValues(CategoryMetadataFieldValues values, Long categoryId, Long fieldId,Locale locale) {
        if (!categoryRepository.findById(categoryId).isPresent()) {
            throw new InvalidDetailException("Invalid Category Id, Does Not Exists In Database");
        }
        if (!categoryMetaDataFieldRepository.findById(fieldId).isPresent()) {
            throw new InvalidDetailException("Invalid MetaDataField Id, Does Not Exists In Database");
        }
        try {
            CategoryMetadataFieldValues newMetaDataFieldValues = new CategoryMetadataFieldValues(
                    categoryMetaDataFieldRepository.findById(fieldId).get(),
                    categoryRepository.findById(categoryId).get(),
                    values.getMetadataValues());
            System.out.println(values.getMetadataValues());
            categoryMetaDataFieldValuesRepository.save(newMetaDataFieldValues);
        } catch (Exception ex) {
            throw new InvalidCategoryOrFieldIdException("Invalid Product Category Id or Metadata Field Id");
        }
        return messageSource.getMessage("values.added.message",null,locale);
    }

    //////////////////////////////////////////////done///////////////////////////////////////////////////////////
    public List<CategoryMetadataFieldDto> viewAllFields() {
        List<CategoryMetaDataField> categoryMetadataField = categoryMetaDataFieldRepository.viewAllFields();
        List<CategoryMetadataFieldDto> metadataFieldDtos = new ArrayList<>();

        for (CategoryMetaDataField fields : categoryMetadataField) {
            CategoryMetadataFieldDto categoryMetadataFieldDto = new CategoryMetadataFieldDto();
            categoryMetadataFieldDto.setId(fields.getId());
            categoryMetadataFieldDto.setName(fields.getName());
            metadataFieldDtos.add(categoryMetadataFieldDto);
        }
        return metadataFieldDtos;
    }

    //////////////////////////////////////////////////done/////////////////////////////////////////////////////////
    public String updateValues(CategoryMetadataFieldValues values, Long categoryId, Long fieldId,Locale locale) {
        if (!categoryRepository.findById(categoryId).isPresent()) {
            throw new InvalidDetailException("Invalid Category Id, Does Not Exists In Database");
        }
        if (!categoryMetaDataFieldRepository.findById(fieldId).isPresent()) {
            throw new InvalidDetailException("Invalid MetaDataField Id, Does Not Exists In Database");
        }
        if (categoryMetaDataFieldValuesRepository.findByValue(values.getMetadataValues()) != null) {
            throw new InvalidDetailException("Values Are Not Unique, Already present");
        }
        try {
            MetadataFieldValueId id = new MetadataFieldValueId(categoryId, fieldId);

            CategoryMetadataFieldValues updatedMetaDataFieldValues = categoryMetaDataFieldValuesRepository.findById(id).get();
            updatedMetaDataFieldValues.setMetadataValues(values.getMetadataValues());
            categoryMetaDataFieldValuesRepository.save(updatedMetaDataFieldValues);
        } catch (Exception ex) {
            throw new InvalidCategoryOrFieldIdException("Invalid Product Category Id or Metadata Field Id");
        }
        return messageSource.getMessage("value.updated.message",null,locale);
    }
}
