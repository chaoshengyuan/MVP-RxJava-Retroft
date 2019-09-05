package com.wenbing.mvpdemo.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wenbing.mvpdemo.Utils.RxUtil;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;
import com.wenbing.mvpdemo.retrofit.ApiServer;
import com.wenbing.mvpdemo.retrofit.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

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

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.add(observable
                    .compose(RxUtil.background())
                    .subscribeWith(observer));
        }
    }

    public void removeDisposable() {
        if (mDisposable != null) {
            // clear时网络请求会随即cancel
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
