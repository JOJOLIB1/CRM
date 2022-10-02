package com.jjj.crm.settings.interceptors;

import com.jjj.crm.commons.constants.Constant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @className: com.jjj.crm.settings.interceptors.LoginInterceptor
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-02 8:28
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 拦截没有登陆过的操作
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return 是否通行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(Constant.SESSION_USER) == null) {
            response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" );
            return false;
        }
        return true;
    }
}
