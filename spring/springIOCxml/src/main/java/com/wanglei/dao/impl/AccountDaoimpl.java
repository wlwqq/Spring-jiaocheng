package com.wanglei.dao.impl;

import com.wanglei.dao.AccountDao;

public class AccountDaoimpl implements AccountDao {

    @Override
    public void findAccount() {
        System.out.println("findAccount() 执行了");
    }
}
