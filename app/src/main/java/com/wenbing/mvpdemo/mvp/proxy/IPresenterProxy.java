package com.wenbing.mvpdemo.mvp.proxy;

import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;
import com.wenbing.mvpdemo.mvp.factory.IPresenterMvpFactory;

/**
 * @author gs_wenbing
 * @date 2019/9/3 18:03
 * 代理接口
 */
public interface IPresenterProxy<V extends BaseMvpView,P extends BaseMvpPresenter<V>> {

    void setPresenterFactory(IPresenterMvpFactory<V,P> factory);

    IPresenterMvpFactory<V,P> getPresentFactory();

    P getMvpPresenter();
}
