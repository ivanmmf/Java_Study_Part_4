package com.example.java_study_part_4.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogTransformationAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

//    @Before(value = "@within(com.example.java_study_part_4.Annotations.LogTransformation) || @annotation(com.example.java_study_part_4.Annotations.LogTransformation)")
//    public void before(JoinPoint joinPoint) {
//        String args = Arrays.stream(joinPoint.getArgs())
//                .map(Object::toString)
//                .collect(Collectors.joining(","));
//        LOGGER.info("class: " + joinPoint.getClass() + " method " + joinPoint.getSignature().getName() + " args: " + args);
//    }

    @Around(value = "@within(com.example.java_study_part_4.Annotations.LogTransformation) || @annotation(com.example.java_study_part_4.Annotations.LogTransformation)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("class: " + joinPoint.getClass() + " method " + joinPoint.getSignature().getName() + " args: " + args + " return value " + joinPoint.proceed().toString());
    }

}
