package ru.itprogram.aspect;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public abstract class Logging {

    protected StringBuilder createLogMessage(JoinPoint joinPoint) {
        StringBuilder message = new StringBuilder("Method: [ ");
        message.append(joinPoint.getSignature().getName()).append(" ]   . ");
        Arrays.stream(joinPoint.getArgs())
                .forEach(arg -> message.append("args: [ ").append(arg).append(" ]. "));
        return message;
    }

}
