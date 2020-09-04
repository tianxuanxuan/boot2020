package com.xgit.boot.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * Created by tianxuanxuan
 * On 2020-09-04 17:11
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> instances);
}
