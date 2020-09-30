package com.xgit.boot.model;

/**
 * Created by tianxuanxuan
 * On 2020-09-30 15:28
 */

import lombok.Data;

/**
 * 用户信息
 */

@Data
public class UserDto {

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String fullname;
}

