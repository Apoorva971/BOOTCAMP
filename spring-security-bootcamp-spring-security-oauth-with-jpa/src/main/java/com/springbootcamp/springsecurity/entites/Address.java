package com.springbootcamp.springsecurity.entites;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Address_id;
    private String City;
    private String Address_Line;
    private Integer Zip_code;
    private String Label;

    @ManyToOne
    private User user;

    public Address(Integer address_id, String city, String address_Line, Integer zip_code, String label) {
        Address_id = address_id;
        City = city;
        Address_Line = address_Line;
        Zip_code = zip_code;
        Label = label;
    }

    public Integer getAddress_id() {
        return Address_id;
    }

    public void setAddress_id(Integer address_id) {
        Address_id = address_id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress_Line() {
        return Address_Line;
    }

    public void setAddress_Line(String address_Line) {
        Address_Line = address_Line;
    }

    public Integer getZip_code() {
        return Zip_code;
    }

    public void setZip_code(Integer zip_code) {
        Zip_code = zip_code;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
