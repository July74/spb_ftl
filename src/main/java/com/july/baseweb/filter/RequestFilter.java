package com.july.baseweb.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 记录每个请求
 *
 * @author July
 * @date 2017/12/19
 */
@WebFilter(filterName = "requestFilter", urlPatterns = "/*")
public class RequestFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (httpServletRequest.getRequestURL().indexOf(".json") > 0) {
            Enumeration<?> parameterNames = httpServletRequest.getParameterNames();
            String method = httpServletRequest.getMethod();
            StringBuilder sb = new StringBuilder("method=").append(method).append("&");
            while (parameterNames.hasMoreElements()) {
                String paran = parameterNames.nextElement().toString();
                sb.append(paran).append("=").append(httpServletRequest.getParameter(paran)).append("&");
            }
            logger.info("url:{}?{}", httpServletRequest.getRequestURL(), sb.toString());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
