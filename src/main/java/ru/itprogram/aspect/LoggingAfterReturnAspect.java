package ru.itprogram.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAfterReturnAspect extends Logging{

    @Pointcut("@annotation(LoggableAfterReturning)")
    public void executeLogging() {
    }

    @AfterReturning(value = "executeLogging()", returning = "returnValue")
    public void logMethodCall(JoinPoint joinPoint, Object returnValue) {
        StringBuilder message = createLogMessage(joinPoint)
                .append("Value: [")
                .append(returnValue)
                .append(" ]");
        log.info(message.toString());
    }

}
