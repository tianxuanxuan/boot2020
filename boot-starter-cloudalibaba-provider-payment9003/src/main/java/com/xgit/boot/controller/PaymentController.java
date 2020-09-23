package com.xgit.boot.controller;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianxuanxuan
 * On 2020-09-23 10:39
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private  String serverPort;

    public static Map<Long, Payment> map = new HashMap<>();
    static{
        map.put(1L, new Payment(1L, "111111"));
        map.put(2L, new Payment(2L, "222222"));
        map.put(3L, new Payment(3L, "333333"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = map.get(id);
        return new CommonResult<>(200, "from mysql,serverPort: " + serverPort, payment);
    }
}
