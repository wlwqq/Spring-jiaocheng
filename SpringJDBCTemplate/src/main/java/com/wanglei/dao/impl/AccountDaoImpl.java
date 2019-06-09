package com.wanglei.dao.impl;

import com.wanglei.dao.AccountDao;
import com.wanglei.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jt;

    public void setJt(JdbcTemplate jt) {
        this.jt = jt;
    }

    @Override
    public void saveAccount(Account account) {
        jt.update("insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
    }

    @Override
    public void deleteAccount(Integer id) {
        jt.update("delete from account where id=?", id);
    }

    @Override
    public void updateAccount(Account account) {
        jt.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }

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
    public Account findById(Integer accountId) {
        List<Account> accountList = jt.query("select * from account where id=?", new BeanPropertyRowMapper<>(Account.class), accountId);
        if (accountList.isEmpty()){
            return null;
        }
        return accountList.get(0);
    }
}
