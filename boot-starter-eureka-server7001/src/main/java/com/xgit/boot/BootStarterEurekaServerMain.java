package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 15:59
 */
@SpringBootApplication
@EnableEurekaServer
public class BootStarterEurekaServerMain {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterEurekaServerMain.class, args);
    }
}