package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.retrofit.error.ApiException;
import com.wenbing.mvpdemo.retrofit.error.ERROR;

import java.lang.ref.SoftReference;

import io.reactivex.observers.DisposableObserver;

/**
 * DisposableObserver
 *
 * @author gs_wenbing
 * @date 2019/9/5 10:27
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    protected SoftReference <IBaseView> mViewReference;
    private boolean mShowLoading = true;
    private String mLoadMessage = "";

    public BaseObserver(IBaseView view) {
        this.mViewReference = new SoftReference<>(view);
    }

    /**
     * @param view        IBaseView
     * @param showLoading 默认显示，showLoading为false 不显示
     */
    public BaseObserver(IBaseView view, boolean showLoading) {
        this.mViewReference = new SoftReference<>(view);
        this.mShowLoading = showLoading;
    }
    /**
     * @param view        IBaseView
     * @param loadMessage 默认显示，loading的提示语
     */
    public BaseObserver(IBaseView view, String loadMessage) {
        this.mViewReference = new SoftReference<>(view);
        this.mLoadMessage = loadMessage;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mViewReference != null && mViewReference.get() != null && mShowLoading) {
            mViewReference.get().showLoading(mLoadMessage);
        }
    }

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
        if (mViewReference != null && mViewReference.get() != null && mShowLoading) {
            mViewReference.get().hideLoading();
        }
    }

    protected abstract void onSuccess(T t);


    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
