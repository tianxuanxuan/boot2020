package com.xgit.boot.service;

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

    public String paymentInfo_TimeOut(Integer id){
        int timeNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池  " + Thread.currentThread().getName() + "  paymentInfo_TimeOut" + id
                + "耗时(秒)" + timeNum;
    }
}
