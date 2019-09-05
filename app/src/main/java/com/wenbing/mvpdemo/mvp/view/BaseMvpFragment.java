package com.wenbing.mvpdemo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.mvp.factory.IPresenterMvpFactory;
import com.wenbing.mvpdemo.mvp.factory.IPresenterMvpFactoryImpl;
import com.wenbing.mvpdemo.mvp.proxy.IPresenterProxyImpl;

/**
 * @author gs_wenbing
 * @date 2019/9/4 15:51
 */
public class BaseMvpFragment<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements IPresenterMvpFactory<V, P> {
    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private IPresenterProxyImpl<V, P> mProxy = new IPresenterProxyImpl<>(IPresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }
    @Override
    public P createMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
