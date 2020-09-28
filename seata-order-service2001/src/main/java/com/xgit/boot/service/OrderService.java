package com.xgit.boot.service;

import com.xgit.boot.domain.Order;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 16:08
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
