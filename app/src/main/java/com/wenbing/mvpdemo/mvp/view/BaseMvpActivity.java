package com.wenbing.mvpdemo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.mvp.factory.IPresenterMvpFactory;
import com.wenbing.mvpdemo.mvp.factory.IPresenterMvpFactoryImpl;
import com.wenbing.mvpdemo.mvp.proxy.IPresenterProxy;
import com.wenbing.mvpdemo.mvp.proxy.IPresenterProxyImpl;

/**
 * @author gs_wenbing
 * @date 2019/9/4 8:58
 */
public abstract class BaseMvpActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends AppCompatActivity implements IPresenterProxy<V, P> {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    private IPresenterProxyImpl<V, P> mProxy = new IPresenterProxyImpl<>(IPresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(IPresenterMvpFactory<V, P> factory) {
        mProxy.setPresenterFactory(factory);
    }

    @Override
    public IPresenterMvpFactory<V, P> getPresentFactory() {
        return mProxy.getPresentFactory();
    }

    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }


}
