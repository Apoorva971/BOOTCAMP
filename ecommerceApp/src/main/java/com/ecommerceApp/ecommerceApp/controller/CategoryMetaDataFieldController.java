package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CategoryMetadataFieldDto;
import com.ecommerceApp.ecommerceApp.entities.CategoryMetaDataField;
import com.ecommerceApp.ecommerceApp.entities.CategoryMetadataFieldValues;
import com.ecommerceApp.ecommerceApp.services.MetaDataFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class CategoryMetaDataFieldController {
    @Autowired
    MetaDataFieldService metaDataFieldService;

    @PostMapping("/admin/add/metadata-field")
    public String addMetadataField(@Valid @RequestBody CategoryMetaDataField categoryMetaDataField, Locale locale){
        return metaDataFieldService.addField(categoryMetaDataField,locale);
    }
    @PostMapping("/admin/add/metadatavalues/{categoryId}/{fieldId}")
    public String addMetadataWithValues(@Valid @RequestBody CategoryMetadataFieldValues values, @PathVariable Long categoryId, @PathVariable Long fieldId,Locale locale){
        return metaDataFieldService.addValues(values,categoryId,fieldId,locale);
    }
    @GetMapping("/admin/field/*")
    public List<CategoryMetadataFieldDto> viewFields(){
        return metaDataFieldService.viewAllFields();
    }

    @PutMapping("/admin/update/metadatavalues/{categoryId}/{fieldId}")
    public String updateMetadataWithValues(@RequestBody CategoryMetadataFieldValues values, @PathVariable Long categoryId, @PathVariable Long fieldId,Locale locale){
        return metaDataFieldService.updateValues(values,categoryId,fieldId,locale);
    }
}
