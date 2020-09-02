package com.xgit.boot.controller;

import com.xgit.boot.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tianxuanxuan
 * On 2020-09-01 16:25
 */
@RestController
public class OrderZkController {
    public final String INVOKE_URL = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "consumerzk/get")
    public String get(){
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk",
                String.class);
    }
}
