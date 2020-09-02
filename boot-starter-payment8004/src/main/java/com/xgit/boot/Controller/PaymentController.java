package com.xgit.boot.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by tianxuanxuan
 * On 2020-08-29 15:22
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "payment/zk")
    public String paymentZk(){
        return "springcloud with zookeeper:" + serverPort + "\t"
                + UUID.randomUUID().toString();
    }
}
