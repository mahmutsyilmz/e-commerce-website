package com.yilmaz.ECommerce.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("isAdmin") == null) {
            // Eğer oturumda admin yetkisi yoksa, kullanıcıyı giriş sayfasına yönlendir
            response.sendRedirect("/api/users/login");
            return false;
        }else {
            return true;
        }
    }
}
