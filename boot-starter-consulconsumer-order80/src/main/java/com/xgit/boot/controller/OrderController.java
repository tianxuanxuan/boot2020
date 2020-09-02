package com.xgit.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tianxuanxuan
 * On 2020-09-02 16:28
 */

@RestController
@Slf4j
public class OrderController {
    public static final String CONSUL_INVOKE = "http://consul-provider-payment";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String get(){
        return restTemplate.getForObject(CONSUL_INVOKE + "/payment/consul", String.class);
    }
}
