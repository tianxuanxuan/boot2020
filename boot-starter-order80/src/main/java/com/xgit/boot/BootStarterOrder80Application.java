package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 11:17
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class BootStarterOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterOrder80Application.class, args);
    }
}