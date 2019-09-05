package com.wenbing.mvpdemo.bean.base;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:57
 */
public class Request<T> {
    private T param;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
