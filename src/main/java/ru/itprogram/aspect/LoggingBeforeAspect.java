package ru.itprogram.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingBeforeAspect extends Logging {

    @Pointcut("@annotation(LoggableBefore)")
    public void executeLogging() {
    }

    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint) {
        log.info(createLogMessage(joinPoint).toString());
    }

}
