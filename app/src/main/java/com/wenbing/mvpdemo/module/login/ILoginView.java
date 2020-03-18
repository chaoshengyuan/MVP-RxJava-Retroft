package com.wenbing.mvpdemo.module.login;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.LoginBean;

/**
 * @author: wenbing
 * @date: 2020/3/16 15:51
 */
public interface ILoginView extends IBaseView {
    void loginSusscee(LoginBean loginBean);
    void loginFailed(String errMsg);
}
