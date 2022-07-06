package com.damon.security.controller;

import com.damon.security.model.AuthenticationRequest;
import com.damon.security.model.UserDto;
import com.damon.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    //produces = "text/plain;charset=utf-8" 规定的就是返回的是一个纯文本形式；
    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        //存入session,注意：此处设置的key相同，就会导致第一个用户登录后，第二个用户登录覆盖调用第一用户登录保存的session信息；
        session.setAttribute(UserDto.SESSION_USER_KEY,userDto);
        return userDto.getUsername() +"登录成功";
    }

    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession session){
        // 此时使用removeAttribute(String name) 可能会更合适
        session.invalidate();
        return "退出成功";
    }

    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session){
        String fullname = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);// 获取
        if(object == null){
            fullname = "匿名";
        }else{
            UserDto userDto = (UserDto) object;
            fullname = userDto.getFullname();
        }
        return fullname+"访问资源r1 ( •̀ .̫ •́ )✧";
    }
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session){
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(userObj != null){
            fullname = ((UserDto)userObj).getFullname();
        }else{
            fullname = "匿名";
        }
        return fullname + " 访问资源2 ( •̀ .̫ •́ )✧";
    }
}
