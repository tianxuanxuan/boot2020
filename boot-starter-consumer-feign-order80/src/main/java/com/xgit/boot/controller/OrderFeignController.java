package com.xgit.boot.controller;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.service.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-07 16:23
 */

@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return orderFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/timeout")
    public String paymentFeignTimeOut(){
        return orderFeignService.paymentFeignTimeOut();
    }
}
