/* *Create an Employee Class with instance variables (String) name, (Integer)age,
 * (String)city and get the instance of the Class using constructor reference
 */
package main.java.com.learning.day5;

class Employee {

    String name;
    Integer age;
    String city;

    Employee(String name, Integer age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;

    }

    public String toString() {
        return name + " " + age + " " + city;
    }
}

@FunctionalInterface
interface EmployeeDetails {
    Employee employeedetail(String name, int age, String city);
}

public class Q4_Employee {
    public static void main(String[] args) {
        EmployeeDetails employeeDetails = Employee::new;//Constructor reference
        System.out.println(employeeDetails.employeedetail("apoorva", 20, "Greater Noida"));
    }
}
