package com.xb.store.config;

import com.xb.store.interceptor.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    loginInterceptor interceptor=new loginInterceptor();
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        loginInterceptor interceptor=new loginInterceptor();
        List<String> list=new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/index.html");
        list.add("/web/login.html");
        list.add("/web/register.html");
        list.add("/web/index.html");
        list.add("/web/product.html");
        list.add("/users/login");
        list.add("/users/reg");
        list.add("/products/**");
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(list);
    }
}
