package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 10:49
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xgit.boot")
public class OrderMain53030 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain53030.class, args);
    }
}
