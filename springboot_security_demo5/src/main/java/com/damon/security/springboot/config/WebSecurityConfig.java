package com.damon.security.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private LoginFailureHandler loginFailureHandler;
    /** 定义用户信息服务（查询用户信息）
     * 	UserDetailsService是用来查询用户信息的类，可以使用默认，也可以自定义这个类；
     * 我们在上个SpringMVC项目中自己实现查询，而SpringSecurity替我们实现了这个查询，
     * 我们只需要告诉SpringSecurity是从内存查还是数据库查；
     * 	此处先使用SpringSecurity提供的UserDetailsService类，从内存查数据信息；
     * 后面还可以设置信息的格式，或者从数据库查数据信息；
     */
/* 被SpringDatauserDetailsService.java代替，使用真实的数据查询操作；
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1","p3","p7").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;
    }*/

    /** 密码编码器
     * 密码编码器的作用
     * 	  用户输入的密码信息格式可能与我们从数据库获取或内存中获取的形式不同，
     * 	  如某一方通过了Hash加密，所以密码编码提供给我们进行密码的操作和比对；
     * @return 返回指定的一种密码编码方式；
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 先返回一种不需要把密码进行任何变换的方式。即进行简单的字符串比对；
        return new BCryptPasswordEncoder();
    }

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 注意这个权限校验顺序，如果把.antMatchers("/r/**").authenticated()放在第一位，那么访问权限失效
        http.csrf().disable()//这里屏蔽掉了对CSRF的抵抗，可以通过其他途径避免，这格项目没有再考虑避免；
                .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                // 所有/r/**的请求必须认证通过才可以,如果没有登录 或者 认证不通过 是不可以访问这个路径的；
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()// 除了/r/**路径下的，其它的请求没登录也可以访问，比如/w/w1下的就可以
            .and()//允许了所有其他访问，并且如果是表单登录操作也可以；
                .formLogin()//允许表单登录
                .loginPage("/login-view")
                .loginProcessingUrl("/login")//当在登录页面点击登录，数据就会传送到后台的login控制器，所也需要在这里配置
                .successForwardUrl("/login-success")//自定义登录成功的页面地址。允许表单登录后的返回页面
                //.failureUrl("/login-view?error=0")
                .failureHandler(loginFailureHandler)
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-view?/logout");
    }
}
