package com.xgit.boot.service.impl;

import com.xgit.boot.dao.PaymentDao;
import com.xgit.boot.entities.Payment;
import com.xgit.boot.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tianxuanxuan
 * On 2020-08-26 17:38
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
