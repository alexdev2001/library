package com.UserManagement.UserManagement.Security;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.UserManagement.UserManagement.Controllers.*.*(..))")
    public void logBeforeRequests() {
        logger.info("A request has been made to the API.");
    }
}
