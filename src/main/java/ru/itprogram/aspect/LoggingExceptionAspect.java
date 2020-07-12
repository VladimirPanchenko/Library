package ru.itprogram.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingExceptionAspect extends Logging {

    @Pointcut("@annotation(LoggableException)")
    public void executeExceptionLogging() {
    }

    @AfterThrowing(pointcut = "executeExceptionLogging()", throwing = "exception")
    public void logMethodCall(JoinPoint joinPoint, Throwable exception) throws RuntimeException{
        StringBuilder message = createLogMessage(joinPoint)
                .append("[ ")
                .append(exception.getCause())
                .append(" ]");
        log.info(message.toString());
    }

}
