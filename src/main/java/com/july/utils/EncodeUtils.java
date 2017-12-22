package com.july.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author July
 */
public class EncodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    public static String md5(String source) {
        StringBuffer md5StrBuff = new StringBuffer(32);

        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.reset();
            e.update(source.getBytes("UTF-8"));
            byte[] byteArray = e.digest();

            for (int i = 0; i < byteArray.length; ++i) {
                if (Integer.toHexString(255 & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
                }
            }
        } catch (Exception var5) {
            logger.error("Can not encode the string " + source + " to MD5!", var5);
            return "";
        }

        return md5StrBuff.toString();
    }
}
