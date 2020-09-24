package com.xgit.boot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tianxuanxuan
 * On 2020-09-23 11:15
 */
@RestController
@Slf4j
public class CircleBreakerController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${server-url.nacos-user-service}")
    private String serverURL;

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback") //负责运行时异常处理
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler") //负责sentinel控制台配置规则
    @SentinelResource(value = "fallback", fallback = "handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = IllegalArgumentException.class) //两种都有，blockHandler 起作用
    public CommonResult<Payment> fallBack(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/paymentSQL/"
                + id, CommonResult.class, id);

        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return  result;
    }

    public CommonResult handlerFallback(@PathVariable Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"异常handlerFallback，exception内容： " + e.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
