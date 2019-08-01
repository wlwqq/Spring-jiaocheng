package com.wanglei.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component(value = "log")
@Aspect
public class Logger {

    @Pointcut(value = "execution(* com.wanglei.service.impl.*.*(..))")
    public void getPoint(){}

    public void printLog(){
        System.out.println("打印日志");
    }

    @Before(value = "getPoint()")
    public void printLog1(){
        System.out.println("打印日志1");
    }
    @AfterReturning(value = "getPoint()")
    public void printLog2(){
        System.out.println("打印日志2");
    }
    @AfterThrowing(value = "getPoint()")
    public void printLog3(){
        System.out.println("打印日志3");

    }
    @After(value = "getPoint()")
    public void printLog4(){
        System.out.println("打印日志4");
    }
}
