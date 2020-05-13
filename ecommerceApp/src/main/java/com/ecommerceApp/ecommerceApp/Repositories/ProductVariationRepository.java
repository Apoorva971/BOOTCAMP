package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.ProductVariation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {

    @Query(value = "select * from ProductVariation",nativeQuery = true)
    Iterable<ProductVariation> findByProductId(Long id);
}
