package com.RestAssignmentproject.rest.webservices.RestAssignment.services;

import com.RestAssignmentproject.rest.webservices.RestAssignment.Q2_Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class Q2_EmployeeServices {
    private static List<Q2_Employee> employees = new ArrayList<>();
    private static int count = 3;

    static {
        employees.add(new Q2_Employee(1, "apoorva", 20));
        employees.add(new Q2_Employee(2, "kittu", 25));
        employees.add(new Q2_Employee(3, "yash", 22));
    }

    /***
     * This method is used to show all the Employee Details
     * @return An object of class Employee
     */

    public List<Q2_Employee> FindAll() {
        return employees;
    }
     /*
    OUTPUT
    [
    {
        "id": 1,
        "name": "apoorva",
        "age": 20
    },
    {
        "id": 2,
        "name": "kittu",
        "age": 25
    },
    {
        "id": 3,
        "name": "yash",
        "age": 22
    }
]
     */

    /***
     * This function is used to check whether id is null if not then increase it by 1
     * @param employee employee is an
     * @return An object of class Employee
     */
    public Q2_Employee save(Q2_Employee employee) {
        if (employee.getId() == null) {
            employee.setId(++count);
        }
        employees.add(employee);
        return employee;
    }
    /*
    OUTPUT
    For Example
    localhost:8080/Employee/1
{
    "id": 1,
    "name": "apoorva",
    "age": 20
}
     */

    /***
     *
     * @param id id which is used to find employee
     * @return an object of Employee class
     */
    public Q2_Employee findone(Integer id) {
        for (Q2_Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    /*
    OUTPUT
    For Example
    localhost:8080/Employee/1
    {
    "id": 1,
    "name": "apoorva",
    "age": 20
}
     */

    /***
     * This method is used to delete a particular Employee Details whose id we have passed
     * @param id id of the employee we want to delete
     * @return if id is present then detele that else return null
     */

    public Q2_Employee deleteById(int id) {
        Iterator<Q2_Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Q2_Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                return employee;
            }
        }
        return null;
    }
    /*
    For Example
    localhost:8080/Employee/1
    it will give the delete the details of first employee from the list
     */

    /***
     * This method is used to update a particular Employee Details whose id we have passed
     * @param id id which we want to add
     * @param employee details of a new employee will be added
     * @return
     */
    public void putEmp(Integer id, Q2_Employee employee){
        Iterator<Q2_Employee> iterator=employees.iterator();
        while(iterator.hasNext()){
            Q2_Employee employee1=iterator.next();
            if(employee1.getId()==id){
                employee1.setName(employee.getName());
                employee1.setAge(employee.getAge());
            }
            /*
            This method will update the details of employee
             */
        }
    }
}

