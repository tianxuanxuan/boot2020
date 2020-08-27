package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by tianxuanxuan
 * On 2020.8.26
 */

@SpringBootApplication
@EnableEurekaClient
public class BootStarterPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterPaymentApplication.class, args);
    }
}
