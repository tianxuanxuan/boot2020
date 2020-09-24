package com.xgit.boot.service;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * Created by tianxuanxuan
 * On 2020-09-24 15:38
 */

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回", new Payment(id, "error serial"));
    }
}
