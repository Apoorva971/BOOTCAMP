package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "select * from Product where id=:productId and seller_user_id=:userId", nativeQuery = true)
    Optional<Product> findByIdAndSellerId(@Param("userId") Long userid, @Param("productId") Long productId);

    @Query(value = "select * from Product where seller_user_id=:userId", nativeQuery = true)
    List<Product> findAllBySeller(@Param("userId") Long userId , Pageable pageable);

    @Query(value = "select * from Product where categoryid=:categoryId", nativeQuery = true)
    List<Product> findAllByCategoryId(@Param("categoryId") Long categoryId);

    Product findByName(String s);

    @Transactional
    @Modifying
    @Query(value = "delete from Product where id=:productId and seller_user_id=:userId",nativeQuery = true)
    void deleteByIdAndSellerId(@Param("productId") Long productId, @Param("userId") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Product set isActive=:isactive where id=:productId",nativeQuery = true)
    void activateProduct(@Param("productId") Long productId,@Param("isactive") boolean isactive);
    @Transactional
    @Modifying
    @Query(value = "update Product set isActive=:isactive where id=:productId",nativeQuery = true)
    void deActivateProduct(@Param("productId") Long productId,@Param("isactive") boolean isactive);
}
