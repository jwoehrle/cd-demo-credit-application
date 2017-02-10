package com.innoq.mploed.ddd.application.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.*;
import static net.logstash.logback.argument.StructuredArguments.entries;
import static net.logstash.logback.argument.StructuredArguments.*;

@Aspect
@Component
public class LoggingAdvices {
    public static Logger LOGGER = LoggerFactory.getLogger(LoggingAdvices.class);

    @Around("com.innoq.mploed.ddd.application.logging.Pointcuts.integrationLayer()")
    public Object integrationPerformanceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        Map structuredArguments = new HashMap();
        structuredArguments.put("log_type", "performance");
        structuredArguments.put("layer", "integration");
        structuredArguments.put("class", proceedingJoinPoint.getTarget().toString());
        structuredArguments.put("time_in_ms", (t2 - t1));
        LOGGER.info("Performance Log: {}", entries(structuredArguments));
        return result;
    }

    @Around("com.innoq.mploed.ddd.application.logging.Pointcuts.persistenceLayer()")
    public Object persistencePerformanceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        Map structuredArguments = new HashMap();
        structuredArguments.put("log_type", "performance");
        structuredArguments.put("layer", "persistence");
        structuredArguments.put("class", proceedingJoinPoint.getTarget().toString());
        structuredArguments.put("time_in_ms", (t2 - t1));
        LOGGER.info("Performance Log: {}", entries(structuredArguments));
        return result;
    }

    @Around("com.innoq.mploed.ddd.application.logging.Pointcuts.webLayer()")
    public Object webPerformanceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        Map structuredArguments = new HashMap();
        structuredArguments.put("log_type", "performance");
        structuredArguments.put("layer", "web");
        structuredArguments.put("class", proceedingJoinPoint.getTarget().toString());
        structuredArguments.put("time_in_ms", (t2 - t1));
        LOGGER.info("Performance Log: {}", entries(structuredArguments));
        return result;
    }

    @After("com.innoq.mploed.ddd.application.logging.Pointcuts.eventPublisher()")
    public void publishedEvents(JoinPoint joinPoint) throws Throwable {
        Map structuredArguments = new HashMap();
        structuredArguments.put("log_type", "publishedEvents");
        structuredArguments.put("event", joinPoint.getArgs()[1].getClass().toString());
        LOGGER.info("Event successfully published: {}", entries(structuredArguments));
    }
}
