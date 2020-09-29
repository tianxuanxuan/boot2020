package com.xgit.boot.service;

import java.math.BigDecimal;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 15:31
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
