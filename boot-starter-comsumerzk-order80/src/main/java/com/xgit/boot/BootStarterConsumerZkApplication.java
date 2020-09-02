package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by tianxuanxuan
 * On 2020-09-01 16:23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BootStarterConsumerZkApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterConsumerZkApplication.class);
    }
}
