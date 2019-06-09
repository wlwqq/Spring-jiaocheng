package com.wanglei.maintest;

import com.wanglei.dao.AccountDao;
import com.wanglei.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = (AccountService) ac.getBean("accountService");
        as.transfer("liuyang","wanglei",100f);
    }
}
