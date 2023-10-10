package com.example.demo.aspects;


import com.example.demo.customers.IEmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAdvice {

    @After(value="execution(* com.example.demo.customers.EmailSender.sendEmail(..)) && args(email, message)")
    public void log(JoinPoint joinpoint, String email, String message){
        System.out.println(new Date() + " methods= "
                + joinpoint.getSignature().getName() + " email address= " + email
        + " message= " + message);
        IEmailSender emailSender = (IEmailSender) joinpoint.getTarget();
        System.out.println("outgoing mail server ="+emailSender.getOutgoingMailServer());
    }
}
