package com.xgit.boot.controller;

import com.xgit.boot.domain.CommonResult;
import com.xgit.boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 15:33
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decrease(Long userId, BigDecimal money){
        //模拟超时异常
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣款成功");
    }
}
