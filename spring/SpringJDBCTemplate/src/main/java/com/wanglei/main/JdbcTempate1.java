package com.wanglei.main;

import com.wanglei.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTempate1 {

    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //1.没有占位符参数的
        jt.execute("insert into account(name,money) values('wanglei',555)");
        //2.带有占位符参数的增删改
        jt.update("insert into account(name,money) values(?,?)", "wqq", 888);
        jt.update("update account set name=?,money=? where id=?","liuyang",789,1);
        jt.update("delete from account where id=?", 2);
        //3.带有占位符参数的查询
        List<Account> list = jt.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        for (Account account:list)
        {
            System.out.println(account);
        }
    }
}
