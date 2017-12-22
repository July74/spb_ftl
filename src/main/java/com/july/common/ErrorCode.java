package com.july.common;

/**
 * @author July
 * @date 2017/12/19
 */
public enum ErrorCode {
    SUCCESS_CODE(0, "成功"), SYS_ERROR(10001, "系统走神了，请稍后再试"), BIZ_ERROR(10002, "业务异常"), PARAM_ERROR(10003, "参数错误"), LOGIN_INVAILD(10004, "登录失效");

    private final int code;

    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
