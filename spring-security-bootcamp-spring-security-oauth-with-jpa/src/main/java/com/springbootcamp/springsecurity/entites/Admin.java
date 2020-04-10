package com.springbootcamp.springsecurity.entites;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Admin extends User {
    public Admin(String first_Name, String last_Name, String middle_Name, String email) {
        super(first_Name, last_Name, middle_Name, email);
    }
}


