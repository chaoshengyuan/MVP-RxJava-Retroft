package com.wenbing.mvpdemo.module.me;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.CoinBean;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:47
 */
public interface IMeView extends IBaseView {
    void showData(CoinBean coinBean);
    void showError(String errorMsg);
}
