package com.wenbing.mvpdemo.bean.base;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:47
 */
public class Response<T> {
    private T data;

    private int result;

    private String errMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
