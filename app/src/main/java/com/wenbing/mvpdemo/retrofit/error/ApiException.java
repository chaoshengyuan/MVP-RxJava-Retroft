package com.wenbing.mvpdemo.retrofit.error;

/**
 * @author gs_wenbing
 * @date 2019/9/5 10:32
 */
public class ApiException extends Exception {
    private int code;
    private String message;
    private Exception e;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        e = (Exception) throwable;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Exception getException() {
        return e;
    }
}