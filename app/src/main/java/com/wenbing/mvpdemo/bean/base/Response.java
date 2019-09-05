package com.wenbing.mvpdemo.bean.base;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:47
 */
public class Response<T> {
    private T body;

    private int code;

    private String errMsg;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
