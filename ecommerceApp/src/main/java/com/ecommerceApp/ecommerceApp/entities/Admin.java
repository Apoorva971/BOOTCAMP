package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Admin extends User {
    public Admin() {

    }

    public Admin(Long id, String firstName, String middleName, String lastName) {
        super(id, firstName, middleName, lastName);
    }
}
