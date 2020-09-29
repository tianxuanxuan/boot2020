package com.xgit.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 15:43
 */
@Configuration
@MapperScan("com.xgit.boot.dao")
public class MyBatisConfig {
}
