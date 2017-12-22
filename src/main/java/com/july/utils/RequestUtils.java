package com.july.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author July
 */
public class RequestUtils {

    public static String getRequestUrlNoContextPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return uri.substring(contextPath.length());
    }

}
