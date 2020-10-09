package com.xgit.boot.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xgit.boot.model.UserDto;
import com.xgit.boot.utils.EncryptUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tianxuanxuan
 * On 2020-09-30 15:22
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    static {
        System.out.println("加载。。。。");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("加载"+request.toString());
        String token = request.getHeader("json-token");

        if (token != null){

            // 1. 解析token
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            JSONObject userJson = JSON.parseObject(json);
           /* UserDto user = new UserDto();
            user.setUsername(userJson.getString("principal"));*/
            UserDto user = JSON.parseObject(userJson.getString("principal"), UserDto.class);
                JSONArray authoritiesArray = userJson.getJSONArray("authorities");
            String[] authorities =authoritiesArray.toArray(new String[authoritiesArray.size()]);

            // 2.新建并填充authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));
            // 3.将authentication保存进spring security安全上下文,方便后面资源中获取用户信息、权限
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        //继续filter
        filterChain.doFilter(request, response);
    }
}
