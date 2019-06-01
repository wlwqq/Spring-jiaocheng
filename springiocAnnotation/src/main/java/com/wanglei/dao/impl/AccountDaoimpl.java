package com.wanglei.dao.impl;

import com.wanglei.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository(value = "accountDao")
public class AccountDaoimpl implements AccountDao {

    @Override
    public void findAccount() {
        System.out.println("findAccount() 执行了");
    }
}
