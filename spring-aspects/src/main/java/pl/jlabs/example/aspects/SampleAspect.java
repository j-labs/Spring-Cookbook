package pl.jlabs.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SampleAspect {

    @Pointcut("execution(* pl.jlabs.example.displayer.DataDisplayer.*(..))")
    private void anyMethodPointcut() {
    }

    @Around("anyMethodPointcut()")
    public Object aroundAnyMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        var signature = joinPoint.getSignature().toString();
        var args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Aspect triggered before " + signature + " method invocation with args " + args);

        var result = joinPoint.proceed();

        System.out.println("Aspect triggered after " + signature + " method invocation with args " + args);
        return result;
    }
}
