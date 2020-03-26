package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="pmode",discriminatorType = DiscriminatorType.STRING)
public  abstract class Payment {
    @Id
    @Column(name = "p_id")
    private int pid;
    private double amount;
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
