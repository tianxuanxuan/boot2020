package com.xgit.boot.controller;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 11:24
 */
@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
         return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment,
                 CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
    }

    @PostMapping("/consumer/payment/update")
    public CommonResult update(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/update", payment,
                CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/delete/" + id,
                CommonResult.class);
    }
}