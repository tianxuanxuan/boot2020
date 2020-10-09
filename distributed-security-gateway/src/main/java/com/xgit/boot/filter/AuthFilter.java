package com.xgit.boot.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xgit.boot.utils.EncryptUtil;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tianxuanxuan
 * On 2020-09-30 10:53
 */

public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    //优先级，0最优先
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /*
         * 1.获取令牌内容
         */
        RequestContext ctx = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 无token访问网关内资源的情况，目前仅有uua服务直接暴露
        if (!(authentication instanceof OAuth2Authentication)){
            return null;
        }
        //判断是OAuth2Authentication，就可以将authentication转为OAuth2Authentication
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();

        if (userAuthentication == null){
            return null;
        }

        //得到请求身份
        String principle = userAuthentication.getName();

        /*
         * 2.组装明文token，转发给微服务，放入header，名称为json-token
         * jdk8 使用map作用于每个元素收集数据
         */
        //得到请求权限
        List<String> authorities = userAuthentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        //请求中其他请求参数
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String, Object> jsonToken = new HashMap<>(requestParameters);

        jsonToken.put("principal", principle);
        jsonToken.put("authorities", authorities);
        ctx.addZuulRequestHeader("json-token",
                EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));
        return null;
    }
}
