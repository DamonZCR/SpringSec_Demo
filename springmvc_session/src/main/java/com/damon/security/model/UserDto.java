package com.damon.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

/**
 * @author Administrator
 * @version 1.0
 **/
@Data
@AllArgsConstructor// 自动生成带有所有参数的构造方法；
public class UserDto {
    public static final String SESSION_USER_KEY = "_user";// 上面也可以使用；
    //用户身份信息
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
    /**
     * 用户权限
     */
    private Set<String> authorities;
}
