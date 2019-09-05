package com.wenbing.mvpdemo.mvp.factory;

import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;

/**
 * @author gs_wenbing
 * @date 2019/9/3 17:44
 */
public interface IPresenterMvpFactory<V extends BaseMvpView, P extends BaseMvpPresenter<V>> {

    P createMvpPresenter();
}
