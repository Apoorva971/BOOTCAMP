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
    @GetMapping(path = "/Welcome-spring-Boot")
    public String WelcomeSpring() {
        return ("Welcome to spring boot");
    }
    //Mapping via bean
    @GetMapping(path="/Welcome-Spring-bean")
    public Q1_SimpleRest q1_simpleRest(){
        return new Q1_SimpleRest("Welcome to spring boot");
    }
}
