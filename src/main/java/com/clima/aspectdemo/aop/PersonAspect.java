package com.clima.aspectdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectConfig {

    @Af
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("AfterThrowing: " + joinPoint.getSignature().getName());
    }

}
