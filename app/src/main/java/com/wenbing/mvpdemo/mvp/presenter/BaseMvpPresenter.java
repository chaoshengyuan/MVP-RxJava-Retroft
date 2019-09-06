package com.wenbing.mvpdemo.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wenbing.mvpdemo.mvp.view.BaseMvpView;
import com.wenbing.mvpdemo.retrofit.ApiServer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author gs_wenbing
 * @date 2019/9/3 14:54
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView> {
    private V mView;

    private CompositeDisposable mDisposable;

    protected ApiServer mApiServer = ApiServer.getInstance();

    public void onCreatePresenter(@Nullable Bundle savedState) {

    }

    public void onAttachView(V view) {
        this.mView = view;
    }

    public void onDetachView() {
        this.mView = null;
    }

    public void onDestroy() {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public boolean isAttached() {
        return this.mView != null;
    }

    public V getMvpView() {
        return mView;
    }

    protected void addDisposable2(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.add(disposable);
        }
    }

    protected void removeDisposable() {
        if (mDisposable != null) {
            // clear时网络请求会随即cancel
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
