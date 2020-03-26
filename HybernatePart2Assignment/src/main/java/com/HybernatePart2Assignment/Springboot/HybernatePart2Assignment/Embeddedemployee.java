package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.*;

@Entity
@Table(name="embedded_employee")
public class Embeddedemployee
{
    @Id
    @GeneratedValue
    int id;
    @Column(name="first_name")
    String firstName;
    @Column(name="last_name")
    String  lastName;
    int age;

    @Embedded
    Salary salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}