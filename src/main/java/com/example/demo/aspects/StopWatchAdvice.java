package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class StopWatchAdvice {

    @Around(value = "execution(* com.example.demo.customers.CustomerDAO.save(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totaltime = sw.getTotalTimeMillis();
        System.out.println("Time to execute " + call.getSignature().getName() + " = " + totaltime + " ms");
        return retVal;
    }

    @Before("execution(* com.example.demo.customers.EmailSender.sendEmail(..))")
    public void preLog(JoinPoint joinPoint){
        System.out.println("Executing " + joinPoint.getSignature().getName());
    }
}
