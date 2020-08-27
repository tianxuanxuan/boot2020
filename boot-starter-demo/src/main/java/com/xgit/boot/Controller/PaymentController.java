package com.xgit.boot.Controller;

import com.xgit.boot.entities.CommonResult;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-08-26 17:45
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果:"+result);
        if (result > 0){
            return new CommonResult<Integer>(200, "插入数据成功", result);
        }else {
            return new CommonResult<Integer>(444, "插入数据失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:" + payment);
        if (payment != null){
            return new CommonResult<Payment>(200, "查询数据成功", payment);
        }else {
            return new CommonResult<Payment>(444, "没有查到对应记录 id："+id);
        }
    }
}
