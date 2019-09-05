package com.wenbing.mvpdemo.mvp.proxy;

import android.os.Bundle;

import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;
import com.wenbing.mvpdemo.mvp.factory.IPresenterMvpFactory;

/**
 * @author gs_wenbing
 * @date 2019/9/3 18:09
 */
public class IPresenterProxyImpl<V extends BaseMvpView,P extends BaseMvpPresenter<V>> implements IPresenterProxy<V,P> {

    private static final String PRESENTER_KEY = "presenter_key";

    private IPresenterMvpFactory<V, P> mFactory;

    private P mPresenter;

    private Bundle mBundle;

    public IPresenterProxyImpl(IPresenterMvpFactory<V, P> factory) {
        this.mFactory = factory;
    }
    @Override
    public void setPresenterFactory(IPresenterMvpFactory<V, P> factory) {
        if (mFactory != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = factory;
    }

    @Override
    public IPresenterMvpFactory<V, P> getPresentFactory() {
        return mFactory;
    }

    @Override
    public P getMvpPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        return mPresenter;
    }

    public void onResume(V mvpView) {
        getMvpPresenter();
        if (mPresenter != null && !mPresenter.isAttached()) {
            mPresenter.onAttachView(mvpView);
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        if (mPresenter != null && mPresenter.isAttached()) {
            mPresenter.onDetachView();
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        if (mPresenter != null ) {
            onDetachMvpView();
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if(mPresenter != null){
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;
    }

}
