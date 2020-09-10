package com.xgit.boot.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tianxuanxuan
 * On 2020-09-10 16:35
 */
@Configuration
public class GatewayConfig {

    /**
     * 函数式编程，输入T：PredicateSpec 即参数r，
     * 输出R：RoteAsyncBuilder 即uri()的返回值类型
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_rute_baidu_guonei",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_rute_baidu_game",
                r -> r.path("/game")
                        .uri("http://news.baidu.com/game")).build();
        return routes.build();
    }
}
