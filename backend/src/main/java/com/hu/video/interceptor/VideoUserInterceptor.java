package com.hu.video.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class VideoUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Object user = request.getSession().getAttribute("user");
            Object admin = request.getSession().getAttribute("admin");
            System.out.println("user = " + user);
            System.out.println("admin = " + admin);
            if(user != null || admin != null) {
                return true;
            }
            response.sendRedirect("/user/toLogin");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
