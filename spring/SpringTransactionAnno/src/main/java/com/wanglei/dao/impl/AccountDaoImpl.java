package com.wanglei.dao.impl;

import com.wanglei.dao.AccountDao;
import com.wanglei.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jt;

    @Override
    public Account findByName(String accountName) {
        List<Account> accountList = jt.query("select * from account where name=?", new BeanPropertyRowMapper<>(Account.class), accountName);
        if (accountList.isEmpty()){
            return null;
        }
        if (accountList.size() > 1){
            throw new RuntimeException("结果集不唯一");
        }
        return accountList.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jt.update("update account set money=? where id=?",account.getMoney(),account.getId());
    }
}
