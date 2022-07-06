package com.damon.security.service;

import com.damon.security.model.AuthenticationRequest;
import com.damon.security.model.UserDto;

/**
 * Created by Administrator.
 */
public interface AuthenticationService {
    /**
     * 用户认证
     * 参数由自己定义的类AuthenticationRequest，包括了：账号和密码
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
