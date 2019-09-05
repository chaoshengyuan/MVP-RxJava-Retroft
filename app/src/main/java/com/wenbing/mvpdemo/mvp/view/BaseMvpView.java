package com.wenbing.mvpdemo.mvp.view;


/**
 * @author gs_wenbing
 * @date 2019/9/3 14:53
 */
public interface BaseMvpView{
    void showLoading();

    void hideLoading();

    void showError(String msg);
}
