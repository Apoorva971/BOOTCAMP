package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class CreditcardperClass extends PaymentperClass
{
    @Column(name = "card_number")
    private String creditnumber;

    public String getCreditnumber() {
        return creditnumber;
    }

    public void setCreditnumber(String creditnumber) {
        this.creditnumber = creditnumber;
    }
}
