package com.ecommerceApp.ecommerceApp.Repositories;

import com.ecommerceApp.ecommerceApp.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByEmail(String email);
    List<Customer> findAll();
    List<Customer> findAll(Pageable pageable);

    @Query(value = "update Customer set isActive=:isactive where email=:email")
    void updateIsActive(@Param("isactive") boolean isActive, String email);
//    @Transactional
//    @Modifying
//    @Query(value = "update Customer set firstName=:firstname ,middleName=:middleName ," +
//            "lastName=:lastName ,contact=:contact where userid=:userid")
//    void updateCustomer(@Param("userid") Long id, @Param("firstname") String firstname,
//                        @Param("middleName") String middleName, @Param("lastName") String lastName,
//                        @Param("contact") Long contact);

}
