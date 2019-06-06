package com.wanglei.service.impl;

import com.wanglei.service.AccountService;
import org.springframework.stereotype.Service;

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Override
    public void saveAcoount() {
        System.out.println("AccountServiceImpl中saveAcoount()执行");
    }
}
