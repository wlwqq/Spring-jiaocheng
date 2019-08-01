package com.wanglei.service.impl;

import com.wanglei.dao.AccountDao;
import com.wanglei.domain.Account;
import com.wanglei.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "accountService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;


    @Override
    public void findByName(String accountName) {
        accountDao.findByName(accountName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
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
