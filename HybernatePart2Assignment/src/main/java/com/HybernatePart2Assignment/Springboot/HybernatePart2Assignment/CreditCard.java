package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cc")
public class CreditCard extends Payment {
    @Column(name = "card_number")
    private String creditnumber;

    public String getCreditnumber() {
        return creditnumber;
    }

    public void setCreditnumber(String creditnumber) {
        this.creditnumber = creditnumber;
    }
}
