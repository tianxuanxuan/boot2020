package com.xgit.boot.controller;

import com.xgit.boot.domain.CommonResult;
import com.xgit.boot.domain.Order;
import com.xgit.boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 16:33
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "创建订单成功");
    }
}
