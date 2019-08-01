package com.wanglei.main;

import com.wanglei.dao.AccountDao;
import com.wanglei.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTempate2 {

    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountDao ad = (AccountDao) ac.getBean("accountDao");
        Account account = ad.findByName("wqq");
        System.out.println(account);
    }
}
