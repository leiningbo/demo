package com.example.demo.config;

import com.example.demo.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 *
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-30 23:05
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

}
