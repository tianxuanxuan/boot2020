package com.xgit.boot.controller;

import com.sun.jndi.toolkit.url.Uri;
import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 11:24
 */
@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
         return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment,
                 CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult get2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(444, "操作失败");
        }
    }

    @PostMapping("/consumer/payment/update")
    public CommonResult update(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/update", payment,
                CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/delete/" + id,
                CommonResult.class);
    }

    @GetMapping(value = "/consumer/discovery")
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

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String zipkin(){
        return restTemplate.getForObject( "http://localhost:8001/payment/zipkin/", String.class);
    }
}
