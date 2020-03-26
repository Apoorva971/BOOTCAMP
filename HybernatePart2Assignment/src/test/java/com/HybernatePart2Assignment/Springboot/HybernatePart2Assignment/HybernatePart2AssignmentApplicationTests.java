package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment.Repository.PaymentjoinedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;


import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class HybernatePart2AssignmentApplicationTests {
    @Autowired
    com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment.EmployeeRepository employeeRepository;
    @Autowired
    com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment.PaymentRepository paymentRepository;
    @Autowired
    com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment.PaymentperclassRepository paymentperclassRepository;
    @Autowired
    EmbeddedEmployeeRepository embeddedEmployeeRepository;
    @Autowired
    PaymentjoinedRepository paymentjoinedRepository;

    @Test
    void contextLoads() {
    }
///////////////////////////////////Inheritance Mapping://///////////////////////////////
///////////////////////    Implement and demonstrate Single Table strategy.
    @Test
    public void testcreate() {
        CreditCard credit = new CreditCard();
        credit.setPid(1);
        credit.setAmount(20000d);
        credit.setCreditnumber("1212423");
        paymentRepository.save(credit);
    }
    /*
    mysql> select *from payment;
+------+-------+--------+-------------+--------------+
| p_id | pmode | amount | card_number | check_number |
+------+-------+--------+-------------+--------------+
|    1 | cc    |  20000 | 1212423     | NULL         |
|    2 | check |  13000 | NULL        | 324234234    |
+------+-------+--------+-------------+--------------+

     */

    @Test
    public void testcheck() {
        Check check = new Check();
        check.setPid(2);
        check.setAmount(13000d);
        check.setChecknumber("324234234");
        paymentRepository.save(check);
    }
    /*
      mysql> select *from payment;
  +------+-------+--------+-------------+--------------+
  | p_id | pmode | amount | card_number | check_number |
  +------+-------+--------+-------------+--------------+
  |    1 | cc    |  20000 | 1212423     | NULL         |
  |    2 | check |  13000 | NULL        | 324234234    |
  +------+-------+--------+-------------+--------------+

       */
//////////////////////////////////Implement and demonstrate Table Per Class strategy.///////////////////////////
    @Test
    public void testcreateperclass() {
        CreditcardperClass creditcardperClass = new CreditcardperClass();
        creditcardperClass.setAmount(35000d);
        creditcardperClass.setCreditnumber("123123");
        paymentperclassRepository.save(creditcardperClass);

        CheckperClass checkperClass = new CheckperClass();

        checkperClass.setAmount(12000d);
        checkperClass.setChecknumber("2422312");
        paymentperclassRepository.save(checkperClass);

    }

    /*

    mysql>select *from card;
    +----+--------+-------------+
    | id | amount | card_number |
    +----+--------+-------------+           ////id is auto generated//////////
    |  7 | 350000 | 123123      |
    |  9 | 350000 | 123123      |
    | 12 | 350000 | 123123      |
    +----+--------+-------------+
    3 rows in set (0.00 sec)

    mysql> select *from bankcheck;
    +----+--------+--------------+
    | id | amount | check_number |
    +----+--------+--------------+             ////id is auto generated//////////
    |  8 | 878262 | 576125371256 |
    | 10 | 878262 | 576125371256 |
    | 13 | 878262 | 576125371256 |
    +----+--------+--------------+

     */
    ////////////////////////////////Implement and demonstrate Joined strategy./////////////////////////////
    @Test
    public void joined_table_inheritance() {
        Cardjoined cardjoined = new Cardjoined();
        cardjoined.setCardnumber("12312412");
        cardjoined.setAmount(12000d);
        Checkjoined checkjoined = new Checkjoined();
        checkjoined.setChecknumber("134345353");
        checkjoined.setAmount(10000d);
        paymentjoinedRepository.save(cardjoined);
        paymentjoinedRepository.save(checkjoined);
    }
/*
mysql> select *from check_joined;
+------+--------------+
| id   | check_number |
+------+--------------+    ////id is auto generated//////////
|   36 | 134345353    |
|   38 | 134345353    |
+------+--------------+

mysql> select *from card_joined;
+------+-------------+
| id   | card_number |
+------+-------------+        ////id is auto generated//////////
|   35 | 12312412    |
|   37 | 12312412    |
+------+-------------+

 */

/*
/////////////////////////////////////////////Component Mapping://////////////////////////////////////////////////////

    Implement and demonstrate Embedded mapping using employee table having following fields:
     id, firstName, lastName, age, basicSalary, bonusSalary, taxAmount, specialAllowanceSalary.

 */
    @Test
    public void embeddeddemofunc() {
        Salary salary = new Salary();
        salary.setBasicSalary(100000);
        salary.setBonusSalary(10000);
        salary.setSpecialAllowanceSalary(50);
        salary.setTaxAmount(30);
        Embeddedemployee embeddedemployee = new Embeddedemployee();
        embeddedemployee.setAge(21);
        embeddedemployee.setFirstName("Apoorva");
        embeddedemployee.setLastName("Garg");
        embeddedemployee.setSalary(salary);
        embeddedEmployeeRepository.save(embeddedemployee);
    }
/*
mysql> select *from embedded_employee;
+----+------------+-----------+------+--------+--------------+--------------+--------------------------+------------+
| id | first_name | last_name | age  | salary | basic_salary | bonus_salary | special_allowance_salary | tax_amount |
+----+------------+-----------+------+--------+--------------+--------------+--------------------------+------------+
| 44 | Apoorva    | Garg      |   21 |   NULL |       100000 |        10000 |                       50 |         30 |
+----+------------+-----------+------+--------+--------------+--------------+--------------------------+------------+

 */
/*
//////////////////////////////////Instructions for JPQL and Native SQL Query/////////////////////////////////////////
        Create an employeeTable table with the following fields:
        empId, empFirstName, empLastName, empSalary, empAge.

        Create an Employee entity having following fields:
        id, firstName, lastName, salary, age which maps to the table columns given in above.

 */
    @Test
    public void create() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstname("Apoorva");
        employee.setLastname("Garg");
        employee.setAge(22);
        employee.setSalary(30000d);

        Employee employee1 = new Employee();
        employee1.setId(2);
        employee1.setFirstname("Shubhanshi");
        employee1.setLastname("Tyagi");
        employee1.setAge(20);
        employee1.setSalary(25000d);

        Employee employee2 = new Employee();
        employee2.setId(3);
        employee2.setFirstname("Jaswant");
        employee2.setLastname("Singh");
        employee2.setAge(24);
        employee2.setSalary(45000d);

        Employee employee3 = new Employee();
        employee3.setId(4);
        employee3.setFirstname("Shantnu");
        employee3.setLastname("Joshi");
        employee3.setAge(76);
        employee3.setSalary(20000d);

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

    }
    /*
    ///////////////////////////////OUTPUT///////////////////////////////////
    +--------+---------------+--------------+---------+------------+
| emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
+--------+---------------+--------------+---------+------------+
|      1 | Apoorva       | Garg         |      22 |      30000 |
|      2 | Shubhanshi    | Tyagi        |      20 |      25000 |
|      3 | Jaswant       | Singh        |      24 |      45000 |
|      4 | Shantnu       | Joshi        |      76 |      20000 |
+--------+---------------+--------------+---------+------------+
     */

    @Test
    public void showDetails() {
        Sort sort = Sort.by(Sort.Order.asc("age"), Sort.Order.desc("salary"));
        List<Object[]> objects = employeeRepository.partialSelect(PageRequest.of(0, 2, sort));
        for (Object[] objects1 : objects) {
            System.out.print(objects1[0] + " ");
            System.out.print(objects1[1] + " ");
            System.out.println();
        }
    }

/*
////////////////////////////////////////////////JPQL:///////////////////////////////////////////////

    Display the first name, last name of all employees having salary greater
     than average salary ordered in ascending by their age and in descending by their salary.
    Update salary of all employees by a salary passed as a parameter whose existing salary is less than the average salary.

 */
    @Transactional
    @Rollback(value = false)
    @Test

    public void updating_Salary_Of_Employees() {
        double average = employeeRepository.findaverage();
        employeeRepository.updateSalary(average, 60000d);
    }

    /*
    //////////////////////BEFORE//////////////////////////////
    +--------+---------------+--------------+---------+------------+
    | emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
    +--------+---------------+--------------+---------+------------+
    |      1 | Apoorva       | Garg         |      22 |      30000 |
    |      2 | Shubhanshi    | Tyagi        |      20 |      25000 |
    |      3 | Jaswant       | Singh        |      24 |      45000 |
    |      4 | Shantnu       | Joshi        |      76 |      20000 |
    +--------+---------------+--------------+---------+------------+
    //////////////////////////////OUTPUT/////////////////////////////////
    +--------+---------------+--------------+---------+------------+
    | emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
    +--------+---------------+--------------+---------+------------+
    |      1 | Apoorva       | Garg         |      22 |      30000 |
    |      2 | Shubhanshi    | Tyagi        |      20 |      60000 |
    |      3 | Jaswant       | Singh        |      24 |      45000 |
    |      4 | Shantnu       | Joshi        |      76 |      60000 |
    +--------+---------------+--------------+---------+------------+

     */
    /*
  //////////////////////////  Delete all employees with minimum salary./////////////////////////
     */
    @Transactional
    @Test
    @Rollback(value = false)
    public void deleting_Employees_Having_Minimum_Salary() {
        double minsal = employeeRepository.findminsal();
        employeeRepository.deletingEmployeesHavingMinimumSalary(minsal);
    }

    /*
    ///////////////////////////BEFORE/////////////////////////////////
+--------+---------------+--------------+---------+------------+
| emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
+--------+---------------+--------------+---------+------------+
|      1 | Apoorva       | Garg         |      22 |      30000 |
|      2 | Shubhanshi    | Tyagi        |      20 |      25000 |
|      3 | Jaswant       | Singh        |      24 |      45000 |
+--------+---------------+--------------+---------+------------+
/////////////////////////OUTPUT//////////////////////////////
+--------+---------------+--------------+---------+------------+
| emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
+--------+---------------+--------------+---------+------------+
|      1 | Apoorva       | Garg         |      22 |      30000 |
|      3 | Jaswant       | Singh        |      24 |      45000 |
+--------+---------------+--------------+---------+------------+

     */
    /*
//////////////////////////////////////Native SQL Query:///////////////////////////////////////////////
  Display the id, first name, age of all employees where last name ends with "singh"
     */
    @Test
    public void find_Employees_Whose_LastName_Ends_With_Singh() {
        List<Object[]> objects = employeeRepository.findEmployeesWhoseLastNameEndsWithSingh();
        for (Object[] objects1 : objects) {
            System.out.print(objects1[0] + " " + objects1[1] + " " + objects1[2]);
            System.out.println();
        }
    }

    /*
    ////////////////////////////before////////////////////////////
    +--------+---------------+--------------+---------+------------+
    | emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
    +--------+---------------+--------------+---------+------------+
    |      1 | Apoorva       | Garg         |      22 |      30000 |
    |      2 | Shubhanshi    | Tyagi        |      20 |      25000 |
    |      3 | Jaswant       | Singh        |      24 |      45000 |
    |      4 | Shantnu       | Joshi        |      76 |      20000 |
    +--------+---------------+--------------+---------+------------+
    //////////////////////////////OUTPUT/////////////////////////////

     3 24 Jaswant
     */
    /*
      Delete all employees with age greater than 45(Should be passed as a parameter)
     */
    @Transactional
    @Test
    @Rollback(value = false)
    public void deleting_Salary_Having_Age_Greater_Than_Passed_Age() {
        employeeRepository.deletingSalaryHavingAgeGreaterThanPassedAge(45);
    }
/*
Before
+--------+---------------+--------------+---------+------------+
| emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
+--------+---------------+--------------+---------+------------+
|      1 | Apoorva       | Garg         |      22 |      30000 |
|      2 | Shubhanshi    | Tyagi        |      20 |      25000 |
|      3 | Jaswant       | Singh        |      24 |      45000 |
|      4 | Shantnu       | Joshi        |      76 |      20000 |
+--------+---------------+--------------+---------+------------+
4 rows in set (0.00 sec)
/////////////////////////////////////AFTER///////////////////////////////////
+--------+---------------+--------------+---------+------------+
| emp_id | emp_firstname | emp_lastname | emp_age | emp_salary |
+--------+---------------+--------------+---------+------------+
|      1 | Apoorva       | Garg         |      22 |      30000 |
|      2 | Shubhanshi    | Tyagi        |      20 |      25000 |
|      3 | Jaswant       | Singh        |      24 |      45000 |
+--------+---------------+--------------+---------+------------+

 */
}
