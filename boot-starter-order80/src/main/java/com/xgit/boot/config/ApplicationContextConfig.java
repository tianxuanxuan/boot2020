package com.xgit.boot.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 11:26
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 注入RestTemplate，实现rest风格的服务调用
     * 指定restTemplate负载均衡，否则无法发现服务
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}