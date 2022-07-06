package com.damon.security.springboot.service;

import com.damon.security.springboot.dao.UserDao;
import com.damon.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 张三密码123，BCrypt为：$2a$10$StU1Llgco9LnLZ0htCSXp.c1FZVeMwGk/CsrgpYDKEAc19RFhkinS
 * 李四密码456，BCrypt为：$2a$10$.4.gJWN9MOHpuqC6mz7bKOvr0hcw13NLO7xzWKG60HlTJ9SqybNx.
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;
        //登录账号
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null){
            // 如果没找到这个用户就返回空，为什么不是直接抛异常呢？
            // 因为根据流程图我们这里只负责将结果返回给DaoAuthenticationProvider，后期的处理包括异常都是它处理；
            return null;
        }
        userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword())
                    .authorities("p1").build();//权限这里还没有实现；需要新建权限表；
        return userDetails;
    }
}

