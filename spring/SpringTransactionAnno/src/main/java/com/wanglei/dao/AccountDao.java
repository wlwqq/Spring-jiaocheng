package com.wanglei.dao;

import com.wanglei.domain.Account;

public interface AccountDao {

    Account findByName(String accountName);
    void updateAccount(Account account);
}
