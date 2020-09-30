package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by tianxuanxuan
 * On 2020-09-30 10:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulMain53010 {
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain53010.class, args);
    }
}
