package com.xgit.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 09:12
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "res1";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
                /*.tokenServices(tokenService())*/
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//关闭session
    }


   /* @Bean 远程验证token配置
    public ResourceServerTokenServices tokenService() {
        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
        RemoteTokenServices service=new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:53020/oauth/check_token");
        service.setClientId("c1");
        service.setClientSecret("secret");
        return service;
    }*/
}
