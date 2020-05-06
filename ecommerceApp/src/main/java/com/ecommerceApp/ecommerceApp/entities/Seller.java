package com.ecommerceApp.ecommerceApp.entities;

import com.ecommerceApp.ecommerceApp.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Seller extends Users{

    private String GST;
    private String companyName;
    private String companyContact;

    @Column(name = "createdDate",nullable = false,updatable =false)
    @CreatedDate
    private Date createdDate;
    @Column(name = "modifiedDate")
    @LastModifiedDate
    private Date modifiedDate;


    @JsonIgnore
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Product> products;


    public Seller() {
        this.addRole(new Role(2l,"ROLE_SELLER"));
    }

    public Seller(String email, String firstName, String middleName, String lastName,
                  String GST, String companyName, String companyContact) {
        super(email, firstName, middleName, lastName);
        this.GST = GST.toUpperCase();
        this.companyName = companyName;
        this.companyContact = companyContact;
        this.addRole(new Role( 2l,"ROLE_SELLER"));
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public Set<Product> getProducts(Pageable pageable) {
        return products;
    }


    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Seller{" +
                super.toString() +
                "GST='" + GST + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyContact='" + companyContact + '\'' +
                '}';
    }

    public void addProduct(Product product){
        if(product != null){
            if(products == null)
                products = new HashSet<Product>();

            products.add(product);

            product.setSeller(this);
        }
    }
}