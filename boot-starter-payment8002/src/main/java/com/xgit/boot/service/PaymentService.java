package com.xgit.boot.service;

import com.xgit.boot.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Created by tianxuanxuan
 * On 2020-08-26 17:28
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
    public int update(Payment payment);
    public int deleteById(Long id);
}
