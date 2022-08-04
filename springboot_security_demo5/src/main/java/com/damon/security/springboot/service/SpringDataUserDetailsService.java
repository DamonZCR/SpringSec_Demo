package com.damon.security.springboot.service;

import com.damon.security.springboot.dao.UserDao;
import com.damon.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 张三密码123，BCrypt为：$2a$10$StU1Llgco9LnLZ0htCSXp.c1FZVeMwGk/CsrgpYDKEAc19RFhkinS
 * 李四密码456，BCrypt为：$2a$10$.4.gJWN9MOHpuqC6mz7bKOvr0hcw13NLO7xzWKG60HlTJ9SqybNx.
 * 这个类实现配置用户信息，将用户的信息配置到UserDetail中；
 *  主要有：查询用户的基本信息通过：userDao.getUserByUsername(username);
 *  userDao.findPermissionsByUserId(userDto.getId());根据用户的账号id，
 *  查询它所对应的角色表，和角色表对应的权限，获得用户的权限，
 *  将上述信息封装到UserDetails中；
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;//可以尝试实现这个类，进行加强；
        //登录账号
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null){
            // 如果没找到这个用户就返回空，为什么不是直接抛异常呢？
            // 因为根据流程图我们这里只负责将结果返回给DaoAuthenticationProvider，后期的处理包括异常都是它处理；
            //return null;此处可以返回一个空的UserDetails让SpringSecurity去解决，会输出异常！

            // 尝试解决如果为空抛出异常的问题
            //throw new BadCredentialsException("用户名不存在");//解决方法一：这个异常还是不够详细
            throw new UsernameNotFoundException("没有该用户");//解决方法二：当用户输入错误的用户名或者密码不会有一场，会前端显示错误
        }
        //根据用户的id查询用户的权限
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);

        System.out.print("用户 " + username + " 拥有的权限包括: ");
        for (int i = 0; i < permissionArray.length; i++) {
            System.out.print(permissionArray[i] +"  ");
        }
        System.out.println();

        // 将用户信息配置到UserDetails中
        userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}

