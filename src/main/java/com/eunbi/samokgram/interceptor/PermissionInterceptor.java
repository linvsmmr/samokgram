package com.eunbi.samokgram.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        HttpSession session = request.getSession();

        Long userId = (Long)session.getAttribute("userId");

        String uri = request.getRequestURI();

        if (userId == null) {
            // memo로 시작하는 요청 url인 경우
            if (uri.startsWith("/home")) {

                response.sendRedirect("/user/login");
                return false;
            }
        } else {
            if (uri.startsWith("/user")) {
                response.sendRedirect("/home/feed");
                return false;
            }
        }

        return true;




    }

}
