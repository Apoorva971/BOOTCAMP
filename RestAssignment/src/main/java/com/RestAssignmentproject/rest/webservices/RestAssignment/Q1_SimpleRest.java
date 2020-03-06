/////////////////////////////QUESTION-1///////////////////////////////////////////
//Create a simple REST ful service in Spring Boot which returns the Response "Welcome to spring boot".
package com.RestAssignmentproject.rest.webservices.RestAssignment;

public class Q1_SimpleRest {
    private String message;

    public Q1_SimpleRest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Q1_SimpleRest{" +
                "message='" + message + '\'' +
                '}';
    }
}
