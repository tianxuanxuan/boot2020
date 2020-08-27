package com.xgit.boot.config;

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
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}