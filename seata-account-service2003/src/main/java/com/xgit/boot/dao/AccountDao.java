package com.xgit.boot.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 15:27
 */
public interface AccountDao {
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
