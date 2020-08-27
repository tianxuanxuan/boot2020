package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by tianxuanxuan
 * On 2020.8.26
 */

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BootStarterPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterPaymentApplication.class, args);
    }
}
