package com.HybernatePart1Assignment.Springboot.HybernatePart1Assignment;

import com.HybernatePart1Assignment.Springboot.HybernatePart1Assignment.Employee;
import com.HybernatePart1Assignment.Springboot.HybernatePart1Assignment.Repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HybernatePart1AssignmentApplicationTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreate() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Apoorva garg");
        employee.setAge(28);
        employee.setLocation("greater noida");
        Employee employee1 = new Employee();
        employee1.setId(2);
        employee1.setName("Shubhanshi Tyagi");
        employee1.setAge(21);
        employee1.setLocation("Delhi");
        Employee employee2 = new Employee();
        employee2.setId(3);
        employee2.setName("Jaswant Singh Shahi");
        employee2.setAge(32);
        employee2.setLocation("Lucknow");
        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
    }
    /////////////////OUTPUT/////////////////////////
    /*
    +----+---------------------+------+---------------+
| id | name                | age  | location      |
+----+---------------------+------+---------------+
|  1 | Apoorva garg        |   22 | greater noida |
|  2 | Shubhanshi Tyagi    |   21 | Delhi         |
|  3 | Jaswant Singh Shahi |   24 | Lucknow       |
+----+---------------------+------+---------------+

     */

    @Test
    void testUpdate() {
        Employee employee = employeeRepository.findById(1).get();
        employee.setAge(20);
        employeeRepository.save(employee);
    }

    /////////////////OUTPUT/////////////////////////
/*

+----+---------------------+------+---------------+
| id | name                | age  | location      |
+----+---------------------+------+---------------+
|  1 | Apoorva garg        |   20 | greater noida |
|  2 | Shubhanshi Tyagi    |   21 | Delhi         |
|  3 | Jaswant Singh Shahi |   24 | Lucknow       |
+----+---------------------+------+---------------+

*/
    @Test
    public void testDelete() {
        if (employeeRepository.existsById(1)) {
            System.out.println("Deleting the details of an employee");
        }
        employeeRepository.deleteById(1);
    }

    /////////////////////////OUTPUT/////////////////////////////
	/*
	+----+---------------------+------+----------+
| id | name                | age  | location |
+----+---------------------+------+----------+
|  2 | Shubhanshi Tyagi    |   21 | Delhi    |
|  3 | Jaswant Singh Shahi |   24 | Lucknow  |
+----+---------------------+------+----------+

	 */
    @Test
    public void testRead() {
        Employee employee = employeeRepository.findById(2).get();
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Shubhanshi Tyagi", employee.getName());
    }

    @Test
    public void testCount() {
        System.out.println("total number of records are>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + employeeRepository.count());
    }

    /*
    /////////////////////////////////////////OUTPUT////////////////////////////////////////
    total number of records are>>>>>>>>>>>>>>>>>>>>>>>>>>>>3

     */
    @Test
    public void testpagingAndSorting() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "age");
        employeeRepository.findAll(pageable).forEach(p -> System.out.println(p.getName()));

    }
   /*
   //////////////////////////////////////////OUTPUT///////////////////////////////////////////
   Jaswant Singh Shahi
   Apoorva garg
    */
    @Test
    public void testFind() {
        List<Employee> employees = employeeRepository.findByName("Shubhanshi Tyagi");
        employees.forEach(employee -> System.out.println(employee.getAge()));
    }
    /*
       //////////////////////////////////////////OUTPUT///////////////////////////////////////////
       21

     */

    @Test
    public void testFindEmp() {
        List<Employee> employees = employeeRepository.findByNameLike("A%");
        employees.forEach(employee -> System.out.println(employee.getAge()));
    }
    /*
    /////////////////////////////////////////OUTPUT////////////////////////////////////////
    22
     */

    @Test
    public void testFindBetween() {
        List<Employee> employees = employeeRepository.findByAgeBetween(28, 32);
        employees.forEach(employee -> System.out.println(employee.getName()));
    }
}
/*
///////////////////////////////////////OUTPUT//////////////////////////////////////////////////
When the input is
    +----+---------------------+------+---------------+
| id | name                | age  | location      |
+----+---------------------+------+---------------+
|  1 | Apoorva garg        |   28 | greater noida |
|  2 | Shubhanshi Tyagi    |   21 | Delhi         |
|  3 | Jaswant Singh Shahi |   32 | Lucknow       |
+----+---------------------+------+---------------+
Then the output is
Apoorva garg
Jaswant Singh Shahi
 */

