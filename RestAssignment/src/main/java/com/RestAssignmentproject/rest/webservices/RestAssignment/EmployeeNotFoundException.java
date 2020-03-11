package com.RestAssignmentproject.rest.webservices.RestAssignment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//////////This is custom Exception class to give our own exception/////////
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
