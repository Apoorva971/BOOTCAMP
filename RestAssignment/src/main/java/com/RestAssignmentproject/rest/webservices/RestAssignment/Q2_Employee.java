/////////////////////////////////////////QUESTION-2//////////////////////////////////////////////
//Create an Employee Bean(id, name, age) and service to  perform different operations related to employee.
package com.RestAssignmentproject.rest.webservices.RestAssignment;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Q2_Employee {
    private Integer id;
    @Size(min=3)
    private String name;
    @Min(value=20)
    private Integer age;

    public Q2_Employee(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Q2_Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
