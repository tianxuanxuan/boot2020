package com.xgit.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 11:18
 */
@Configuration
public class TokenConfig {
    //令牌存储策略，基于内存
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
