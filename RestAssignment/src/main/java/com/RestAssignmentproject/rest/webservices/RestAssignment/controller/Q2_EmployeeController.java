package com.RestAssignmentproject.rest.webservices.RestAssignment.controller;

import com.RestAssignmentproject.rest.webservices.RestAssignment.EmployeeNotFoundException;
import com.RestAssignmentproject.rest.webservices.RestAssignment.Q2_Employee;
import com.RestAssignmentproject.rest.webservices.RestAssignment.services.Q2_EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class Q2_EmployeeController {
    @Autowired
    public Q2_EmployeeServices services;


     //////////////////////////QUESTION-3//////////////////////////////
    // Implement GET http request for Employee to get list of employees.
    @GetMapping("/Employees")
    public List<Q2_Employee> retrieveAlluser() {
        return services.FindAll();

    }
    /*
    ///////////////////////OUTPUT/////////////////////////
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
     //////////////////////////QUSETION-4///////////////////////////////////
    //  Implement GET http request using path variable top get one employee
    @GetMapping("/Employees/{id}")
    public Q2_Employee retrieveuser(@PathVariable int id) {
        return services.findone(id);
    }

    /////////////////////////QUESTION-5/////////////////////////////////////
    // Implement POST http request for Employee to create a new employee.
    @PostMapping("/Employees")
    public void createmployee(@RequestBody Q2_Employee employee) {
        Q2_Employee savedEmployee = services.save(employee);
    }
    /*
 //////////////////////////////////////////OUTPUT///////////////////////////
 localhost:8080/Employees/1
    {
    "id": 1,
    "name": "apoorva",
    "age": 20
}
     */

     //////////////////////////QUESTION-6/////////////////////////////////////
    //Implement 404 NOT_FOUND Exception
    @GetMapping("/Employee/{id}")
    public Q2_Employee retrieveuserwithException(@PathVariable int id) {
        Q2_Employee employee = services.findone(id);
        if (employee == null) {
   ////////////////////EmployeeNotFoundException is a custom exception class//////////
            throw new EmployeeNotFoundException("id" + id);
        }
        return employee;
    }
    /*
    //////////////////////////OUTPUT//////////////////////////
  localhost:8080/Employee/4
    {
    "timestamp": "2020-03-31T10:25:31.499+0000",
    "status": 404,
    "error": "Not Found",
    "message": "id4",
    "path": "/Employee/4"
}
     */

    ///////////////////////////QUSETION-7/////////////////////////////////////
    //Implement DELETE http request for Employee to delete employee
    @DeleteMapping("/Employees/{id}")
    public void deletemployee(@PathVariable int id) {
        Q2_Employee employee = services.deleteById(id);
    }
/*
/////////////////////////OUTPUT/////////////////////
[
    {
        "id": 2,
        "name": "kittu",
        "age": 25
    },
    {                                //when employee 1 is deleted
        "id": 3,
        "name": "yash",
        "age": 22
    }
]
 */

    //////////////////////////QUESTION-8//////////////////////////////////
    // Implement PUT http request for Employee to update employee
    @PutMapping(path="/Employee/{id}")
    public void putEmployee(@PathVariable Integer id,@Valid @RequestBody  Q2_Employee employee){
        services.putEmp(id,employee);
    }

    ////////////////////////////QUESTION-9//////////////////////////////////
    //Apply validation while create a new employee using POST http Request.
    @PostMapping("/Employee")
    public ResponseEntity<Object> create(@Valid @RequestBody Q2_Employee employee) {
        Q2_Employee save = services.save(employee);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
  /*

    //////////////////////////QUESTION-10//////////////////////////////////////
   Configure actuator in your project to check the health of application and
   get the information about various beans configured in your application

           *In This we have to give the dependency of actuator in tha build.gradle
           *along with the dependency of hal browser
                 **OUTPUT
                          _links
                                  self
                                   href	"http://localhost:8080/actuator"
                                   templated	false
                                   health
                                   href	"http://localhost:8080/actuator/health"
                                   templated	false
                                   health-path
                                   href	"http://localhost:8080/actuator/health/{*path}"
                                   templated	true
                                   info
                                   href	"http://localhost:8080/actuator/info"
                                   templated	false

  */

}
