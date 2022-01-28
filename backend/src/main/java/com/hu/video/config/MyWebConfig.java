package com.hu.video.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/video/**").addResourceLocations("file:F:\\code\\design\\postDesign\\src\\main\\resources\\static\\video\\");
        registry.addResourceHandler("/static/uimages/**").addResourceLocations("file:F:\\code\\design\\postDesign\\src\\main\\resources\\static\\uimages\\");
    }
}
