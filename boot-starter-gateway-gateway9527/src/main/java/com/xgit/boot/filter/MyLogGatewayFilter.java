package com.xgit.boot.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * Created by tianxuanxuan
 * On 2020-09-11 15:54
 */

@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("******* Come in MyLogGatewayFilter:" + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if (uname == null){
            log.info("******用户名为空，非法用户******");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器优先级，0优先级最高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
