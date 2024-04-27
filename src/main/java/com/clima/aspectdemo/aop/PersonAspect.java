package com.clima.aspectdemo.aop;

import com.clima.aspectdemo.entity.Person;
import com.clima.aspectdemo.util.CheckLevelPermission;
import com.clima.aspectdemo.util.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PersonAspect {

    @AfterThrowing(value = "execution(* com.clima.aspectdemo.service.PersonService.readFromFile())", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("AfterThrowing: {}. Cause: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }

    @Around("@annotation(logExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.info("Executed {} in {} ms", logExecutionTime.value(), executionTime);

        return proceed;
    }

    @Before("@annotation(logExecutionTime)")
    public void before(LogExecutionTime logExecutionTime) {
        log.info("Executing before {}.", logExecutionTime.value());
        log.info(logExecutionTime.value());
    }

    @Before("@annotation(checkLevelPermission) && args(id)")
    public void before(CheckLevelPermission checkLevelPermission, int id) {
        log.info("Checking permission before: {}, module: {}", id, checkLevelPermission.value().name());
    }

    @AfterReturning(value = "execution(* com.clima.aspectdemo.service.PersonService.updatePerson(..))", returning = "person")
    public void afterPersonUpdate(Person person) {
        log.info("Updating person: {}", person.toString());
    }

    @After(value = "execution(* com.clima.aspectdemo.service.PersonService.deleteById(..)) && args(id)")
    public void afterDelete(JoinPoint joinPoint, int id) {
        log.info("Executed: {} from {} with id: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                id);
    }

    @Pointcut("execution(* com.clima.aspectdemo.service.PersonService.createPerson(..))")
    public void personCreationPoint() {
        //pointcut to creating persson
    }

}
