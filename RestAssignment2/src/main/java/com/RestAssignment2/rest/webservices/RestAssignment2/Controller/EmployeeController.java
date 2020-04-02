package com.RestAssignment2.rest.webservices.RestAssignment2.Controller;

import com.RestAssignment2.rest.webservices.RestAssignment2.MyClasses.EmployeesV1;
import com.RestAssignment2.rest.webservices.RestAssignment2.MyClasses.EmployeesV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    //using URI versioning
    @GetMapping("/Employees/v1")
    public EmployeesV1 getEmployeesV1() {
        return new EmployeesV1(001, "Apoorva", "ttn");
    }

    /*
    ///////////////////////////////OUTPUT/////////////////////////
    localhost:8080/Employees/v1
    {
        "id": 1,
        "name": "Apoorva",
        "password": "ttn"
    }
     */
    //using URI versioning
    @GetMapping("/Employees/v2")
    public EmployeesV2 getEmployeesV2() {
        return new EmployeesV2(001, "Apoorva", "apoorva.garg@tothenew.com", "ttn");
    }
        /*
        ///////////////////////////////OUTPUT/////////////////////////
        localhost:8080/Employees/v1
   {
    "id": 1,
    "name": "Apoorva",
    "email": "apoorva.garg@tothenew.com",
    "password": "ttn"
}
         */

    //using Request versioning
    @GetMapping(value = "/Employees/param", params = "version=1")
    public EmployeesV1 paramV1() {
        return new EmployeesV1(001, "Apoorva", "ttn");
    }
     /*
    ////////////////////////OUTPUT///////////////////////////
    localhost:8080/Employees/param?version=2
    {
    "id": 1,
    "name": "Apoorva",
    "password": "ttn"
}
     */

    //using Request versioning
    @GetMapping(value = "/Employees/param", params = "version=2")
    public EmployeesV2 paramV2() {
        return new EmployeesV2(001, "Apoorva", "apoorva.garg@tothenew.com", "ttn");
    }
    /*
    ////////////////////////OUTPUT///////////////////////////
    localhost:8080/Employees/param?version=2
    {
    "id": 1,
    "name": "Apoorva",
    "email": "apoorva.garg@tothenew.com",
    "password": "ttn"
}
     */

    //using Header versioning
    @GetMapping(value = "/Employees/header", headers = "hver=1")
    public EmployeesV1 headerV1() {
        return new EmployeesV1(001, "Apoorva", "ttn");
    }
/*
////////////////////////////////OUTPUT/////////////////////
localhost:8080/Employees/header
when hver =1
{
    "id": 1,
    "name": "Apoorva",
    "email": "apoorva.garg@tothenew.com",
    "password": "ttn"
}
 */
    //using Header versioning
    @GetMapping(value = "/Employees/header", headers = "hver=2")
    public EmployeesV2 headerV2() {
        return new EmployeesV2(001, "Apoorva", "apoorva.garg@tothenew.com", "ttn");
    }
    /*
////////////////////////////////OUTPUT/////////////////////
localhost:8080/Employees/header
when hver = 2
{
    "id": 1,
    "name": "Apoorva",
    "email": "apoorva.garg@tothenew.com",
    "password": "ttn"
}
 */

    //using Producer versioning
    @GetMapping(value = "/Employees/param", produces = "application/v1+json")
    public EmployeesV1 mimeV1() {
        return new EmployeesV1(001, "Apoorva", "ttn");
    }
    /*
    ////////////////////////////////OUTPUT/////////////////////
localhost:8080/Employees/param?produces=application/v1+json
    {
    "id": 1,
    "name": "Apoorva",
    "password": "ttn"
}

     */
    //using Header versioning
    @GetMapping(value = "/Employees/param", produces = "application/v2+json")
    public EmployeesV2 mimeV2() {
        return new EmployeesV2(001, "Apoorva", "apoorva.garg@tothenew.com", "ttn");
    }
}
/*
////////////////////////////////OUTPUT/////////////////////
localhost:8080/Employees/param?produces=application/v2+json
        {
    "id": 1,
    "name": "Apoorva",
    "email": "apoorva.garg@tothenew.com",
    "password": "ttn"
}

        */

