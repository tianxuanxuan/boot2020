package com.xgit.boot.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianxuanxuan
 * On 2020-09-07 17:40
 */

@Service
public class PaymentService {
    public String paymentInfo_ok(Integer id){
        return "线程池  " + Thread.currentThread().getName() + "  paymentInfo_ok  " + id;
    }

    /**
     * 演示服务超时，服务异常后进行服务降级
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(Integer id){
        //int age = 10/0; //演示异常降级
        int timeNum = 3;
        try { TimeUnit.SECONDS.sleep(timeNum); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池  " + Thread.currentThread().getName() + "  paymentInfo_TimeOut" + id
                + "耗时(秒)" + timeNum;
    }

    /**
     *降级方法必须和调用方法有同样的参数列表？
     */
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池  " + Thread.currentThread().getName() + "  调用8001支付服务超时或出错";
    }
}
