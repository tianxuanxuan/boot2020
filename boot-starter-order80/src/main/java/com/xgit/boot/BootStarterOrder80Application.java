package com.xgit.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by tianxuanxuan
 * On 2020-08-27 11:17
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BootStarterOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterOrder80Application.class, args);
    }
}