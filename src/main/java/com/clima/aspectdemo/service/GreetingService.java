package com.clima.aspectdemo.service;

import com.clima.aspectdemo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
@Service
public class GreetingService {

    @AfterReturning(pointcut = "com.clima.aspectdemo.aop.PersonAspect.personCreationPoint()",
            returning = "person")
    public void sayHello(Person person) {
        log.info("Hello {} {}", person.getFirstName(), person.getLastName());
    }

}
