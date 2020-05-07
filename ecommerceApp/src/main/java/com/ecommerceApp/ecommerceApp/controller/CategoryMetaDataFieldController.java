package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.CategoryMetadataFieldDto;
import com.ecommerceApp.ecommerceApp.entities.CategoryMetaDataField;
import com.ecommerceApp.ecommerceApp.entities.CategoryMetadataFieldValues;
import com.ecommerceApp.ecommerceApp.services.MetaDataFieldService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    @ApiOperation(value = "Api to add the CategoryMetaDataFeild ", authorizations = {@Authorization("Bearer")})
    @PostMapping(value = "/admin/add/metadata-field",produces = "application/json")
    public String addMetadataField(@Valid @RequestBody CategoryMetaDataField categoryMetaDataField, Locale locale){
        return metaDataFieldService.addField(categoryMetaDataField,locale);
    }
    @ApiOperation(value = "Api to add the CategoryMetaDataFeilld Values", authorizations = {@Authorization("Bearer")})
    @PostMapping(value = "/admin/add/metadatavalues/{categoryId}/{fieldId}",produces = "application/json")
    public String addMetadataWithValues(@Valid @RequestBody CategoryMetadataFieldValues values, @PathVariable Long categoryId, @PathVariable Long fieldId,Locale locale){
        return metaDataFieldService.addValues(values,categoryId,fieldId,locale);
    }
    @ApiOperation(value = "Api to view all the CategoryMetaDataFeild", authorizations = {@Authorization("Bearer")})
    @GetMapping(value = "/admin/field/*",produces =  "application/json")
    public List<CategoryMetadataFieldDto> viewFields(){
        return metaDataFieldService.viewAllFields();
    }

    @ApiOperation(value = "Api to update the CategoryMetaDataFeild Value", authorizations = {@Authorization("Bearer")})
    @PutMapping("/admin/update/metadatavalues/{categoryId}/{fieldId}")
    public String updateMetadataWithValues(@RequestBody CategoryMetadataFieldValues values, @PathVariable Long categoryId, @PathVariable Long fieldId,Locale locale){
        return metaDataFieldService.updateValues(values,categoryId,fieldId,locale);
    }
}
