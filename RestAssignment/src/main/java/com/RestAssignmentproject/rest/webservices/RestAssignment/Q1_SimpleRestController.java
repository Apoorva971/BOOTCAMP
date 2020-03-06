////////////////////////////////////QUESTION-1/////////////////////////////////////////////////
//Create a simple REST ful service in Spring Boot which returns the Response "Welcome to spring boot".
/*
//localhost:8080/Welcome-spring-Boot
OUTPUT
Welcome to spring boot
 */
package com.RestAssignmentproject.rest.webservices.RestAssignment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class Q1_SimpleRestController {
    //By Getmapping we have maped the function with the browser
    @GetMapping("")
    public String WelcomeSpring() {
        return ("Welcome to spring boot");
    }
}
