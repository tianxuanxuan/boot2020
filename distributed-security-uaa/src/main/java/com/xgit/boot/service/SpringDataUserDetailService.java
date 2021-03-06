package com.xgit.boot.service;

import com.alibaba.fastjson.JSON;
import com.xgit.boot.dao.UserDao;
import com.xgit.boot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianxuanxuan
 * On 2020-09-27 09:06
 */
@Service
public class SpringDataUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userDao.getUSerByUsername(username);
        if (user == null){
            return null;//返回null，由provider抛异常
        }
        //将user转为string，扩展用户信息
        String principle = JSON.toJSONString(user);
        //权限
        List<String> permissions = userDao.findPermissionByUserId(user.getId());
        String[] permissionsArr = new String[permissions.size()];
        permissions.toArray(permissionsArr);

        //将来采用查数据库的方式验证
        return User.withUsername(principle)
                .password(user.getPassword())
                .authorities(permissionsArr)
                .build();
    }
}
