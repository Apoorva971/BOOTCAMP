package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.CategoryMetaDataField;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetaDataFieldRepository extends CrudRepository<CategoryMetaDataField,Long> {
    @Query(value = "select *from CategoryMetaDataField where Name=:metafieldName",nativeQuery = true)
    CategoryMetaDataField findByName(@Param("metafieldName")String metadatafield);
    @Query(value = "from CategoryMetaDataField")
    List<CategoryMetaDataField> viewAllFields();
}
