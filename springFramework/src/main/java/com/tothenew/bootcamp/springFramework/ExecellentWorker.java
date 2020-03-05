package com.tothenew.bootcamp.springFramework;

import org.springframework.stereotype.Component;

@Component
public class ExecellentWorker implements Worker{
    public void dowork(){
        System.out.println("execellent worker is working");
    }
}
