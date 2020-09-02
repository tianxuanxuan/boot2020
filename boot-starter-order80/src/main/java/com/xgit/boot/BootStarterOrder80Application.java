package com.xgit.boot;

import com.xgit.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 11:17
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class) //使用自定义负载均衡算法
public class BootStarterOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterOrder80Application.class, args);
    }
}