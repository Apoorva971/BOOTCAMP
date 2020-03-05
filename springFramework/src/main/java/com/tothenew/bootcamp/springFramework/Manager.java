package com.tothenew.bootcamp.springFramework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Manager {
    @Autowired
    private Worker work;
    public  Manager(Worker work){
     this.work=work;
    }
    public void managework(){
        work.dowork();
    }
}
