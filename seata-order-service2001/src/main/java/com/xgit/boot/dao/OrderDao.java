package com.xgit.boot.dao;

import com.xgit.boot.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 15:50
 */
@Mapper
public interface OrderDao {
    //创建订单
    int create(Order order);

    //修改订单状态 0->1
    int update(@Param("userId") Long userId, @Param("status") Integer status);
}
