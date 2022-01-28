package com.hu.video.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    VideoUserInterceptor videoUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册 userInterceptor
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(videoUserInterceptor);
        interceptorRegistration.addPathPatterns("/video/videoPlay**");
    }
}
