package com.july.common;

/**
 * @author Administrator
 */
public class CommonResponse<T> {
    private T data;

    private int code = 0;

    private String msg = "ok";

    public static <T> CommonResponse successWithData(T data) {
        CommonResponse response = new CommonResponse();
        response.setData(data);
        return response;
    }

    public static CommonResponse success() {
        return new CommonResponse();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
