package com.wenbing.mvpdemo.view;

import com.wenbing.mvpdemo.bean.LoginResponse;
import com.wenbing.mvpdemo.mvp.view.BaseMvpView;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:35
 */
public interface TestView extends BaseMvpView {
    void setBtnEnabled(boolean enabled);

    void setData(LoginResponse loginResponse);
}
