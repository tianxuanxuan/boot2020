package com.xgit.boot.service;

import org.springframework.stereotype.Service;

/**
 * Created by tianxuanxuan
 * On 2020-09-08 17:35
 */
@Service
public class PaymentFallBackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "-----PaymentFallBackService-paymentInfo_ok  通用降级-----";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallBackService-paymentInfo_TimeOut  通用降级-----";
    }
}
