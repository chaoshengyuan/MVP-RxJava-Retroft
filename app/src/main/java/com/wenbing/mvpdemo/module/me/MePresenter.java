package com.wenbing.mvpdemo.module.me;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.CoinBean;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:47
 */
public class MePresenter extends BasePresenter<IMeView> {
    public void requestData(){
        if(getView()==null){
            return;
        }
        mApiServer.toSubscribe(mApiServer.getApi().getUserCoin(),
                new BaseObserver<CoinBean>(getView(),false) {
                    @Override
                    protected void onSuccess(CoinBean coinBean) {
                        if (getView() != null) {
                            getView().showData(coinBean);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        if (getView() != null) {
                            getView().showError(ex.getMessage());
                        }
                    }
                });
    }
}
