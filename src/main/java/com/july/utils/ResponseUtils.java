package com.july.utils;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author July
 */
public class ResponseUtils {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    public static void writeJson(HttpServletResponse response, Object obj) {
        try {
            response.setContentType("application/json");
            response.getWriter().write(JSON.toJSONString(obj));
        } catch (IOException var3) {
            logger.error("writeJson error. obj:" + obj, var3);
        }

    }
}
