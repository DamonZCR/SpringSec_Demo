package com.damon.security.interceptor;

import com.damon.security.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * SpringMVC提供了一个拦截器接口HandlerInterceptor，我们可以实现这个接口；
 * @author Administrator
 * @version 1.0
 **/
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    // preHandle方法代表在调用所有Controller方法之间，执行这个方法；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这个方法中校验用户请求的url是否在用户的权限范围内
        //取出用户身份信息
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if(object == null){
            //没有认证，提示登录
            writeContent(response,"请登录");
            return false;//说明用户没有登录信息，就拒绝方法或其他拒绝方式，不加此行将报错；
        }
        UserDto userDto = (UserDto) object;
        //请求的url
        String requestURI = request.getRequestURI();
        if( userDto.getAuthorities().contains("p1") && requestURI.contains("/r/r1")){
            return true;
        }
        if( userDto.getAuthorities().contains("p2") && requestURI.contains("/r/r2")){
            return true;
        }
        writeContent(response,userDto.getFullname() + " 没有相应权限，拒绝访问 ⊙﹏⊙∥");

        return false;
    }

    //响应信息给客户端，使用的主要参数就是 HttpServletResponse
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }
}
