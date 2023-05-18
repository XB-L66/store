package com.xb.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
    @Around("execution(* com.xb.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long begin=System.currentTimeMillis();
        Object proceed = point.proceed();
        long end=System.currentTimeMillis();
        System.out.println("方法执行了"+(end-begin)+"毫秒");
        return proceed;
    }
}
