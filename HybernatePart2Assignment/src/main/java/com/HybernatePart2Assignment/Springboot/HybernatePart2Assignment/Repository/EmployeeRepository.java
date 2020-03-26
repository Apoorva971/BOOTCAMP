package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

    @Query("select Firstname, Lastname from Employee where salary >(select AVG(salary) from Employee)")
    List<Object[]> partialSelect(Pageable pageable);

    @Query(" select AVG(salary) from Employee")
    double findaverage();


    @Transactional
    @Query("update Employee set salary=:nsal where salary<:avg")
    @Modifying
    void updateSalary( @Param("avg") double avg,@Param("nsal") double nsal);

    @Query("select min(salary) from Employee")
    int findminsal();

    @Transactional
    @Query(value = "delete from Employee where salary=:salary")
    @Modifying
    void deletingEmployeesHavingMinimumSalary(@Param("salary") double salary);

    @Query(value = "select emp_id,emp_age,emp_firstname from employee_table where emp_lastname like '%Singh'",
            nativeQuery = true)
    List<Object[]> findEmployeesWhoseLastNameEndsWithSingh();

    @Modifying
    @Transactional
    @Query(value = "delete from employee_table where emp_age >:emp_age",nativeQuery = true)
    void deletingSalaryHavingAgeGreaterThanPassedAge(@Param("emp_age") int emp_age);


}
