package com.xgit.boot.controller;

import com.xgit.boot.model.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 08:50
 */
@RestController
public class OrderController {
    @GetMapping("/r/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1(){
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getFullname() + "访问资源1";
    }
}
