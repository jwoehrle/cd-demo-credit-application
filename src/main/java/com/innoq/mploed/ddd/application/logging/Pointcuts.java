package com.innoq.mploed.ddd.application.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Pointcuts {
    @Pointcut("execution(* com.innoq.mploed.ddd.application.integration.*.*(..))")
    public void integrationLayer(){}

    @Pointcut("execution(* com.innoq.mploed.ddd.application.events.EventPublisher.*(..))")
    public void eventPublisher(){}

    @Pointcut("execution(* com.innoq.mploed.ddd.application.repository.*.*(..))")
    public void persistenceLayer(){}

    @Pointcut("execution(* com.innoq.mploed.ddd.application.controller.*.*(..))")
    public void webLayer(){}
}
