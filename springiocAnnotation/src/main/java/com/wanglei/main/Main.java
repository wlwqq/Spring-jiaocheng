package com.wanglei.main;

import com.wanglei.config.SpringConfiguration;
import com.wanglei.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1.获取核心容器
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2.获取accountService对象
        AccountService accountService = (AccountService) ioc.getBean("accountService");
        //3.调用findAccount()方法
        accountService.findAccount();
    }
}
