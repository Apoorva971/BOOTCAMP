package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name="card_joined")
public class Cardjoined extends Paymentjoined {
    @Column(name="card_number")
    private String Cardnumber;

    public String getCardnumber() {
        return Cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        Cardnumber = cardnumber;
    }
}
