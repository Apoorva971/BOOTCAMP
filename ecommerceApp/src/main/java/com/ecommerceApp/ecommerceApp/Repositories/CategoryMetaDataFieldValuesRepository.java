package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.CategoryMetadataFieldValues;
import com.ecommerceApp.ecommerceApp.entities.MetadataFieldValueId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryMetaDataFieldValuesRepository extends CrudRepository<
        CategoryMetadataFieldValues, MetadataFieldValueId> {
    @Query(value = "select *from CategoryMetadataFieldValues where metadataValues=:value",nativeQuery = true)
    CategoryMetadataFieldValues findByValue(@Param("value") String value);

    @Query(value = "update CategoryMetadataFieldValues set metadataValues=:value where " +
            " id=:categoryid and Id=:metadatafeildid ",nativeQuery = true)
    void updateMetadataValues(@Param("value") String metadataValues, @Param("categoryid") Long category, @Param("metadatafeildid") Long categorymetadatafeild);

    @Query(value = "select * from CategoryMetadataFieldValues where categoryMetadDataFieldId=:fieldId AND categoryId=:categoryId ",nativeQuery = true)
    CategoryMetadataFieldValues findMetadataFieldValue(@Param("fieldId")Long fieldId,@Param("categoryId")Long categoryId);
}
