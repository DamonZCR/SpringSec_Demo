package com.damon.security.springboot.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public LoginFailureHandler(){
        this.setDefaultFailureUrl("/login-view?error=0");
    }
}
