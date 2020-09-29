package com.xgit.boot.service.impl;

import com.xgit.boot.dao.AccountDao;
import com.xgit.boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 15:31
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountDao.decrease(userId, money);
    }
}
