package com.xgit.boot.service;

import com.xgit.boot.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tianxuanxuan
 * On 2020-09-28 16:09
 */
@FeignClient("seata-storage-service")
public interface StorageService {
    /**
     * 减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count);
}
