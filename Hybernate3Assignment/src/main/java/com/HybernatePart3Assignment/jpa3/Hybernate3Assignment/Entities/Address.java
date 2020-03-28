package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;

import javax.persistence.Embeddable;

@Embeddable

public class Address {
    String streetNumber;
    String location;
    String State;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
