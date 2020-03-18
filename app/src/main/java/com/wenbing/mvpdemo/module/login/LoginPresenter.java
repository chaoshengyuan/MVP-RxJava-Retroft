package com.wenbing.mvpdemo.module.login;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.LoginBean;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;
import com.wenbing.mvpdemo.utils.UserUtils;

/**
 * @author: wenbing
 * @date: 2020/3/16 15:50
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public void requestData(String username,String password){
        if (getView() == null) {
            return;
        }
        mApiServer.toSubscribe(mApiServer.getApi().login(username, password),
                new BaseObserver<LoginBean>(getView(),false) {
                    @Override
                    protected void onSuccess(LoginBean loginBean) {
                        UserUtils.getInstance().login(loginBean);
                        if (getView() != null) {
                            getView().loginSusscee(loginBean);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        if (getView() != null) {
                            getView().loginFailed(ex.getMessage());
                        }
                    }
                });
    }
}
