package com.wanglei.service.impl;

import com.wanglei.dao.AccountDao;
import com.wanglei.domain.Account;
import com.wanglei.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void findByName(String accountName) {
        accountDao.findByName(accountName);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        Account source = accountDao.findByName(sourceName);
        Account target = accountDao.findByName(targetName);

        if (source.getMoney() < money){
            throw new RuntimeException("金额不足");
        }
        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);

        accountDao.updateAccount(source);
        int i = 1/0;
        accountDao.updateAccount(target);
    }
}
