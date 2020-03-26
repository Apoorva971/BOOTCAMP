package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "payment_class")
public abstract class PaymentperClass {
    @Id
    @GeneratedValue
    int id;
    double amount;

    public PaymentperClass() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
