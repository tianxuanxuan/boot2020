package com.xgit.boot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.handler.BlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-23 09:57
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试",
                new Payment(2020L, "1234"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444, exception.getClass().getCanonicalName()
                + "\t" + "服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return  new CommonResult(200,
                "按照byUrl限流测试",new Payment(2020L,"serial002"));
    }

    @GetMapping("/rateLimit/blockHandler")
    @SentinelResource(value = "blockHandler", blockHandlerClass = BlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult blockHandler(){
        return  new CommonResult(200,
                "按照自定义",new Payment(2020L,"serial003"));
    }
}
