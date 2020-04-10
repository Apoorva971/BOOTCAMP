package com.springbootcamp.springsecurity.entites;

import javax.persistence.Entity;

@Entity
public class Customer extends User {
    private Long Contact;

    public Customer(String first_Name, String last_Name, String middle_Name, String email) {
        super(first_Name, last_Name, middle_Name, email);
    }

    public Customer(String first_Name, String last_Name, String middle_Name, String email, Long contact) {
        super(first_Name, last_Name, middle_Name, email);
        Contact = contact;
    }

    public Long getContact() {
        return Contact;
    }

    public void setContact(Long contact) {
        Contact = contact;
    }


    }


