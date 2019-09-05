package com.wenbing.mvpdemo.retrofit.error;

/**
 * @desc 自定义异常
 * @auther zwb
 * create at 2017/2/9 14:07
 */

public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}