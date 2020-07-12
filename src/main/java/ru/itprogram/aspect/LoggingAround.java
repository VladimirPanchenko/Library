package ru.itprogram.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAround {

    @Pointcut("@annotation(LoggableAround)")
    public void executeLogging() {
    }

    @Around("executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();

        Object returnValue = proceedingJoinPoint.proceed();
        StringBuilder message = new StringBuilder("Method: ");
        message.append(proceedingJoinPoint.getSignature().getName());
        Long spendTime = System.currentTimeMillis() - startTime;
        message.append(" time: " + spendTime + " milliseconds");
        log.info(message.toString());

        return returnValue;
    }

}
