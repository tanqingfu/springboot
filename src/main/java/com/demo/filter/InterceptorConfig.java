package com.demo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//SpringBoot2.0及Spring 5.0 WebMvcConfigurerAdapter已被废弃  改用WebMvcConfigurationSupport
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    /*1:创建一个新类，InterceptorConfig
    2:实现WebMvcConfigurer
    3:在其类上加入@Configuration注解
    4:重写addInterceptors方法
    5:在方法内加入拦截规则，如registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");*/
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**");
       // super.addInterceptors(registry);
    }
}
