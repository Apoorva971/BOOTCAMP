package com.RestAssignment2.rest.webservices.RestAssignment2.MyClasses;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;


@ApiModel(description="Model of a user.")
@JsonFilter("ignoring")
public class User {
    private int id;
    private String name;
    private int age;
    @JsonIgnore
    private String password;

    public User(int id, String name, int age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
