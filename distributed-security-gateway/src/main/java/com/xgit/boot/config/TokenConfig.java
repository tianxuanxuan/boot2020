package com.xgit.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 11:18
 */
@Configuration
public class TokenConfig {
    public final String SIGNING_KEY = "uaa123"; //对称加密，秘钥和权限服务一致
    //令牌存储策略，基于内存
   /* @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }*/

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);//对称秘钥，资源服务器使用该秘钥进行验证
        return converter;
    }
}
