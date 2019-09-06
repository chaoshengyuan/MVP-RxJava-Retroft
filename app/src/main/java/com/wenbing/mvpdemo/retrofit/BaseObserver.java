package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.bean.base.Response;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;
import com.wenbing.mvpdemo.retrofit.error.ApiException;
import com.wenbing.mvpdemo.retrofit.error.ERROR;

import io.reactivex.observers.DisposableObserver;

/**
 * @author gs_wenbing
 * @date 2019/9/5 10:27
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {


    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            e.printStackTrace();
            ApiException exception = new ApiException(e, ERROR.UNKNOWN);
            exception.setMessage(e.getMessage());
            onError(exception);
        }
        onComplete();
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onSuccess(T t);


    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}