package ru.otus.shepin.spring.logging;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.otus.shepin.spring.service.io.OutputService;

@Component
@Aspect
@AllArgsConstructor
public class LoggingAspect {
    private final OutputService outputService;

    @Before(value = "@annotation(ru.otus.shepin.spring.logging.LogMe)")
    public void logBefore(JoinPoint joinPoint) {
        outputService.print("-");
        outputService.print("Call class : " + joinPoint.getTarget().getClass().getSimpleName());
        outputService.print("Call method : " + joinPoint.getSignature().getName());
        outputService.print("-");
    }
}
