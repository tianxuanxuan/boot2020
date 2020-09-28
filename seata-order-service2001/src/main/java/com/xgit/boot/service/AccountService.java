package com.xgit.boot.service;

import com.xgit.boot.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 16:09
 */
@FeignClient("seata-account-service")
public interface AccountService {
    /**
     * 减余额
     * @param userId
     * @param money
     * @return
     */
    @GetMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money") BigDecimal money);
}
