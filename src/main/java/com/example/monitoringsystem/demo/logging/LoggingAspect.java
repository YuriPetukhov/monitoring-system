package com.example.monitoringsystem.demo.logging;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.monitoringsystem.service.impl.MetricsServiceImpl.*(..))", throwing = "ex")
    public void logError(Exception ex) {
        logger.error("Error occurred: {}", ex.getMessage());
    }
}

