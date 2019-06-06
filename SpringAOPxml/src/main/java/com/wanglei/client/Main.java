package com.wanglei.client;

import com.wanglei.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = (AccountService) ac.getBean("accountService");
        as.saveAcoount();
    }
}
