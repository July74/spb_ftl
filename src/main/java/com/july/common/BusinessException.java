package com.july.common;

/**
 * @author July
 */
public class BusinessException extends RuntimeException {

    private int code = 500; // 默认服务器内部异常.

    public BusinessException() {
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMsg(), cause);
        this.code = errorCode.getCode();
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        // 不填充异常堆栈信息（防止在远程接口调用出现业务异常时，需要传输大量的堆栈信息）
        return null;
    }
}
