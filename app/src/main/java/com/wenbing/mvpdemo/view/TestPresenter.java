package com.wenbing.mvpdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wenbing.mvpdemo.bean.LoginRequest;
import com.wenbing.mvpdemo.bean.LoginResponse;
import com.wenbing.mvpdemo.bean.base.Request;
import com.wenbing.mvpdemo.mvp.presenter.BaseMvpPresenter;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:35
 */
public class TestPresenter extends BaseMvpPresenter<TestView> {

    void requestData() {
        if(!isAttached()){
            return;
        }
        getMvpView().showLoading();
        getMvpView().setBtnEnabled(false);
        Request<LoginRequest> request = new Request<>();
        LoginRequest login = new LoginRequest();
        login.setUserNo("zhangke");
        login.setPassword("123456");
        request.setParam(login);
        addDisposable2(mApiServer.login(request,new BaseObserver<LoginResponse>() {
            @Override
            protected void onSuccess(LoginResponse loginResponse) {
                getMvpView().setData(loginResponse);
            }

            @Override
            protected void onError(ApiException ex) {
                getMvpView().showError(ex.getMessage());
            }

            @Override
            public void onComplete() {
                getMvpView().setBtnEnabled(true);
                getMvpView().hideLoading();
            }
        }));
    }

    @Override
    public void onCreatePresenter(@Nullable Bundle savedState) {
        super.onCreatePresenter(savedState);
        if(savedState!=null){
            System.out.println(savedState.getString("test"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test","test_save");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeDisposable();
    }
}
