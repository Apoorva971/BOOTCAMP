package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bankcheck")
public class CheckperClass extends PaymentperClass {
    @Column(name = "check_number")
    private String checknumber;

    public String getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber;
    }
}
