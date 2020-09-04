package com.xgit.boot.Controller;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianxuanxuan
 * On 2020-08-26 17:45
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果:"+result);
        if (result > 0){
            return new CommonResult<Integer>(200, "插入数据成功serverPort:"
                    + serverPort, result);
        }else {
            return new CommonResult<Integer>(444, "插入数据失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:" + payment);
        if (payment != null){
            return new CommonResult<Payment>(200, "查询数据成功serverPort:"
                    + serverPort, payment);
        }else {
            return new CommonResult<Payment>(444, "没有查到对应记录 id："+id + ",serverPort:"
                    + serverPort);
        }
    }

    @PostMapping(value = "/payment/update")
    public CommonResult<Integer> update(@RequestBody Payment payment){
        int result = paymentService.update(payment);
        log.info("*****更新结果:"+result);
        if (result > 0){
            return new CommonResult<Integer>(200, "更新数据成功serverPort:"
                    + serverPort, result);
        }else {
            return new CommonResult<Integer>(444, "更新数据失败serverPort:"
                    +serverPort);
        }
    }

    @GetMapping(value = "/payment/delete/{id}")
    public CommonResult<Integer> deleteById(@PathVariable("id") Long id){
        int result = paymentService.deleteById(id);
        log.info("*****删除结果:"+result);
        if (result > 0){
            return new CommonResult<Integer>(200, "删除数据成功serverPort:"
                    + serverPort, result);
        }else {
            return new CommonResult<Integer>(444, "删除数据失败serverPort:"
                    + serverPort);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        discoveryClient
                .getServices()
                .forEach(s -> log.info("*****element:" + s));

        discoveryClient
                .getInstances("cloud-payment-service")
                .forEach(instance -> log.info(instance.getServiceId()+"\t" + instance.getHost() +
                        "\t" + instance.getPort() +"\t" + instance.getUri()));

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
}
