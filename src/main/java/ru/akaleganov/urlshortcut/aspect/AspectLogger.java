package ru.akaleganov.urlshortcut.aspect;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * аспект для логирования сервисов
 */
@Aspect
@Component
public class AspectLogger {

    private static final Logger LOGGER = Logger.getLogger(AspectLogger.class);

    @Pointcut("execution(* ru.akaleganov.urlshortcut.service.*.*(..))")
    private void allLogService() {
    }

    @Pointcut("execution(* ru.akaleganov.urlshortcut.web.rest.*.*(..))")
    private void allLogRest() {
    }

    @Before(value = "allLogService() || allLogRest() ")
    private void beforeLog(JoinPoint joinPoint) {
        LOGGER.info("Выполнился метод:" + joinPoint.getTarget().getClass().getSimpleName() + " "
                            + joinPoint.getSignature().getName());
        String args = Arrays.stream(joinPoint.getArgs()).filter(Objects::nonNull)
                            .map(Object::toString)
                            .collect(Collectors.joining(","));
        LOGGER.info("Входящие параметры: " + joinPoint.toString() + ", args=[" + args + "]");
    }

    @AfterReturning(value = "allLogService() || allLogRest()", returning = "ret")
    private void afterLog(Object ret) {
        LOGGER.info("Возвращаемое значение: " + ret);
    }

}
