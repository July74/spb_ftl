package com.july.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author July
 */
public class CookieUtils {

    public static String getCookieValue(String name, HttpServletRequest request) {
        String value = "";
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equalsIgnoreCase(name)) {
                    value = cookies[i].getValue();
                }
            }
        }

        return value;
    }

    public static void setNewCookie(String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(604800);
        response.addCookie(cookie);
    }
}
