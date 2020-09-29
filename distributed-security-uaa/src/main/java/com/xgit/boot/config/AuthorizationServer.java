package com.xgit.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 10:59
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //客户端详情 clientDetailService
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
        /*clients.inMemory()
                .withClient("c1")//客户端id
                .secret(new BCryptPasswordEncoder().encode("secret"))//客户端秘钥
                .resourceIds("res1")//客户端可以访问的资源列表
                .authorizedGrantTypes("authorization_code","password",
                        "client_credentials", "implicit", "refresh_token")//允许访问的授权类型
                .scopes("all")//允许授权的范围
                .autoApprove(false)//false 跳转到授权页面
                //验证回调地址
                .redirectUris("http://www.baidu.com");*/
    }

    //令牌访问端点和令牌服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)//密码模式需要
                .authorizationCodeServices(authorizationCodeServices)//授权码模式需要
                .tokenServices(tokenServices())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    //令牌端点安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")// /oauth/token_key 公开
                .checkTokenAccess("permitAll()")// /oauth/check_token 公开
                .allowFormAuthenticationForClients();//允许表单认证
    }

    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService); //客户端详情
        services.setSupportRefreshToken(true);//是否产生刷新令牌
        services.setTokenStore(tokenStore);//令牌存储位置
        //令牌增强
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter));
        services.setTokenEnhancer(chain);
        services.setAccessTokenValiditySeconds(7200);//有效期，两小时
        services.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效期3天
        return services;
    }

    /*@Bean //授权码模式-存在内存
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }*/

    @Bean //授权码模式-存在db
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) { //数据源在yml中已经配置
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }
}
