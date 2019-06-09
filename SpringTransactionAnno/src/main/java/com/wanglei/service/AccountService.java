package com.wanglei.service;

public interface AccountService {

    void findByName(String accountName);
    void transfer(String sourceName, String targetName, Float money);
}
