package com.xgit.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tianxuanxuan
 * On 2020-09-02 17:57
 */

@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule(); // 负载均衡：随机
    }
}
