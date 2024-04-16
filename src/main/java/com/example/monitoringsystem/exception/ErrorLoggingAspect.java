package com.example.monitoringsystem.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorLoggingAspect {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @AfterThrowing(pointcut = "execution(* com.example.monitoringsystem.demo.service.*.*(..))", throwing = "ex")
    public void logError(Exception ex) {
        String errorMessage = "Error occurred: " + ex.getMessage();
        kafkaTemplate.send("error-topic", errorMessage);
    }
}

