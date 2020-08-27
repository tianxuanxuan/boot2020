package com.xgit.boot.dao;

import com.xgit.boot.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by tianxuanxuan
 * On 2020-08-26 17:25
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
    public int update(Payment payment);
    public int deleteById(@Param("id") Long id);
}
