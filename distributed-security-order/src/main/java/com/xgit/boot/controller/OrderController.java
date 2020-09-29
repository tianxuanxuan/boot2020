package com.xgit.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 08:50
 */
@RestController
public class OrderController {
    @GetMapping("/r/r1")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String r1(){
        return "访问资源1";
    }
}
