package com.july.baseweb.interceptor;

import com.july.common.BusinessException;
import com.july.common.Constant;
import com.july.common.ErrorCode;
import com.july.common.annotion.RequireLogin;
import com.july.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 *
 * @author July
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(CsrfAttackInterceptor.class);

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getServletPath().startsWith("/user/login")) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.getMethodAnnotation(RequireLogin.class) == null) {
                return true;
            }
        }
        int userId = 1;
        try {
//            userId = request.getIntHeader("userId");
        } catch (RuntimeException e) {
            logger.info("userId非法，userId: {}", request.getHeader("userId"));
            throw new BusinessException(ErrorCode.LOGIN_INVAILD);
        }
        String cookieToken = CookieUtils.getCookieValue(Constant.COOKIE_TOKEN_NAME, request);
        String token = redisTemplate.opsForValue().get("token" + userId);
        if (cookieToken.equals(token)) {
            return true;
        }
        response.sendRedirect("user/login");
        return false;
    }
}
