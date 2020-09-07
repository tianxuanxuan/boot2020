package com.xgit.boot.service;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by tianxuanxuan
 * On 2020-09-07 16:21
 */

@Service
@FeignClient(value = "cloud-payment-service")
public interface OrderFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeout")
    public String paymentFeignTimeOut();
}
