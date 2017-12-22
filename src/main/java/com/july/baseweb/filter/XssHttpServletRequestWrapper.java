package com.july.baseweb.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author July
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest servletRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.servletRequest = request;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }

        return value;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }

        return value;
    }

    private static String xssEncode(String s) {
        if (s != null && !s.isEmpty()) {
            StringBuilder sb = new StringBuilder(s.length() + 16);

            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                switch (c) {
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    default:
                        sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return s;
        }
    }

    public HttpServletRequest getServletRequest() {
        return this.servletRequest;
    }

    public static HttpServletRequest getServletRequest(HttpServletRequest req) {
        return req instanceof XssHttpServletRequestWrapper ? ((XssHttpServletRequestWrapper) req).getServletRequest() : req;
    }
}
