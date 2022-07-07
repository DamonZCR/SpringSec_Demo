package com.damon.security.springboot.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return getUsername() + " 登录成功";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        return getUsername() + " 访问资源r1   ( •̀ .̫ •́ )✧";
    }

    /**
     * 测试资源2
     * @return
     */
    //@PreAuthorize("hasAuthority('p1')")基于方法的授权使用。
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        return getUsername() + " 访问资源r2   ( •̀ .̫ •́ )✧";
    }
    @GetMapping(value = "/w/w1",produces = {"text/plain;charset=UTF-8"})
    public String w1(){
        return getUsername() + " 访问无需授权的资源  ヾ(•ω•`)o";
    }

    //获取当前用户信息
    private String getUsername(){
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份。通过getPrincipal()方法获取UserDetails对象
        Object principal = authentication.getPrincipal();
        if(principal == null){
            username = "匿名";
        }
        if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else{
            username = principal.toString();
        }
        return username;
    }
}