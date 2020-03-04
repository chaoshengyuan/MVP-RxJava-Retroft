package com.wenbing.mvpdemo.base;

/**
 * 每一个View基本需要的一些操作 ，是BaseActivity和BasePresenter之前的桥梁
 *
 * @author gs_wenbing
 * @date 2020/3/4 10:16
 */
public interface IBaseView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg);
}
