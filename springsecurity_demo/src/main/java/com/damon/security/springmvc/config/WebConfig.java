package com.damon.security.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration//就相当于springmvc.xml文件
@EnableWebMvc
@ComponentScan(basePackages = "com.damon.security.springmvc"
        ,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class WebConfig implements WebMvcConfigurer {

    //解析器
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /** 使用redirect:/login的作用是
         *      因为SpringSecurity为我们提供了登录页面，当我们初次进入项目页面的时候就是进入了
         *     SpringSecurity提供的登录页面。然而如果我们使用setViewName("/login");
         *     我们想从页面回到登录页面时，这个方法返回的就是login.jsp视图，然后我们并没有定义这个
         *     视图，因为它是SpringSecurity提供的，所以我们需要使用redirect去通知它返回login视图；
         */
        registry.addViewController("/").setViewName("redirect:/login");
    }

}
