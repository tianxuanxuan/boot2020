package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.*;

/**
 * Created by tianxuanxuan
 * On 2020-08-29 15:20
 */

@SpringBootApplication
@EnableDiscoveryClient ////该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class BootStarterPayment4Application {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterPayment4Application.class, args);
    }
}
