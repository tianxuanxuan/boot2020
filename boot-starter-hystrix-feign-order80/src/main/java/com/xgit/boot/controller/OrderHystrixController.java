package com.xgit.boot.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xgit.boot.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-08 15:22
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand //全局fallback
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int age = 10/0;  //演示异常报错
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "线程池：  " + Thread.currentThread().getName() + "  我是order，调用8001支付服务超时或出错";
    }

    public String paymentInfo_Global_FallbackMethod(){
        return "Global 异常信息处理，请稍后再试！";
    }
}
