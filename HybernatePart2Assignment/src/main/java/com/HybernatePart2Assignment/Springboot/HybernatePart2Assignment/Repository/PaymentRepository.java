package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentRepository extends CrudRepository<Payment,Integer> {


}
