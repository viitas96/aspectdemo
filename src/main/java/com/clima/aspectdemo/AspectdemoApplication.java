package com.clima.aspectdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class AspectdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectdemoApplication.class, args);
    }

}
