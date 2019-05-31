package com.wanglei.service.impl;

import com.wanglei.dao.AccountDao;
import com.wanglei.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void findAccount() {
        accountDao.findAccount();
    }
}
