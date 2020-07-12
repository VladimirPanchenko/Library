package ru.itprogram.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAfterAspect extends Logging{

    @Pointcut("@annotation(LoggableAfter)")
    public void executeLogging() {
    }

    @After("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint) {
        log.info(createLogMessage(joinPoint).toString());
    }

}
