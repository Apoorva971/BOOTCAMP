package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name ="payment_joined")
public abstract class Paymentjoined {
    @Id
    @GeneratedValue
    private int id;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
