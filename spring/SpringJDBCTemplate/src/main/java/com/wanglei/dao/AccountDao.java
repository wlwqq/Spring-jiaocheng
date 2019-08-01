package com.wanglei.dao;

import com.wanglei.domain.Account;

public interface AccountDao {

    void saveAccount(Account account);
    void deleteAccount(Integer id);
    void updateAccount(Account account);

    Account findByName(String accountName);
    Account findById(Integer accountId);
}
