package com.tothenew.bootcamp.springFramework;

import org.openjsse.sun.security.rsa.RSAUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LazyWorker1 implements Worker{
    @Override
    public void dowork() {
        System.out.println("lazy is doing work");
    }
}
