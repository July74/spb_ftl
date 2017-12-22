package com.july.baseweb.interceptor;

import com.july.common.CommonResponse;
import com.july.common.ErrorCode;
import com.july.utils.CookieUtils;
import com.july.utils.EncodeUtils;
import com.july.utils.RequestUtils;
import com.july.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止csrf
 *
 * @author July
 */
@Component
public class CsrfAttackInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CsrfAttackInterceptor.class);

    private static final String CSRF_TOKEN_KEY_NAME = "csrftoken";

    public static final String CSRF_COOKIE_KEY_NAME = "_skey";

    @Value("${csrf.open}")
    private boolean open;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String debug = request.getParameter("debug");
        if (debug == null && this.open) {
            String uriWithoutContx = RequestUtils.getRequestUrlNoContextPath(request);
            if (uriWithoutContx.endsWith(".json") && !uriWithoutContx.contains("login.json")) {
                String method = request.getMethod();
                if (StringUtils.isNotEmpty(method) && StringUtils.equalsIgnoreCase(method, "POST")) {
                    String csrfToken = "";
                    if (request instanceof MultipartHttpServletRequest) {
                        return true;
                    }

                    csrfToken = request.getParameter(CSRF_TOKEN_KEY_NAME);
                    String cookieToken = CookieUtils.getCookieValue(CSRF_COOKIE_KEY_NAME, request);
                    if (StringUtils.isNotEmpty(csrfToken) && StringUtils.isNotEmpty(cookieToken)) {
                        if (StringUtils.equalsIgnoreCase(csrfToken, cookieToken)) {
                            this.refreshTokenCookies(response);
                            return true;
                        }

                        logger.error("url:" + uriWithoutContx + ",csrftoken:" + csrfToken + ",cookieToken:" + cookieToken + " invalid");
                    } else {
                        logger.error("url:" + uriWithoutContx + "csrftoken or cookietoken is null");
                    }

                    this.refreshTokenCookies(response);
                    CommonResponse resp = new CommonResponse();
                    resp.setCode(Integer.valueOf(ErrorCode.PARAM_ERROR.getCode()));
                    resp.setMsg("csrftoken令牌参数不合法");
                    ResponseUtils.writeJson(response, resp);
                    return false;
                }

                this.refreshTokenCookies(response);
            }

            return true;
        } else {
            return true;
        }
    }

    private void refreshTokenCookies(HttpServletResponse response) {
        String csrfToken = EncodeUtils.md5(String.valueOf(System.currentTimeMillis()));
        CookieUtils.setNewCookie(CSRF_COOKIE_KEY_NAME, csrfToken, response);
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
