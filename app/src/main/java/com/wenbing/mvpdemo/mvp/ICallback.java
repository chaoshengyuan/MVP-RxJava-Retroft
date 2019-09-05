package com.wenbing.mvpdemo.mvp;

import com.wenbing.mvpdemo.bean.base.Response;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:45
 */
public interface ICallback<T> {
    void onResponse(Response<T> response);

    void onFailure(String msg);

    void onComplete();
}
