package com.xgit.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 09:38
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // .antMatchers("/r/r1").hasAuthority("p2") 基于web的授权不用，因为我们用的是基于方法的授权
                // .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/order/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll();//"/order/r/**"，其它的请求可以访问
    }
}
