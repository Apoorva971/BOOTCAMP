package com.springbootcamp.springsecurity.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Seller extends User {

    private String GST;
    private Long companyContact;
    private String companyName;


    public Seller(String first_Name, String last_Name
            , String middle_Name, String email, String GST, Long companyContact, String companyName) {
        super(first_Name, last_Name, middle_Name, email);
        this.GST = GST;
        this.companyContact = companyContact;
        this.companyName = companyName;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public Long getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(Long companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
