package com.HybernatePart2Assignment.Springboot.HybernatePart2Assignment;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("check")
public class Check extends Payment {
    @Column(name = "check_number")
    private String checknumber;

    public String getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber;
    }
}
