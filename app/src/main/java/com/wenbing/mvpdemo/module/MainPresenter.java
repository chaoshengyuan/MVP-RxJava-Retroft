package com.wenbing.mvpdemo.module;


import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.Banner;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

import java.util.List;

/**
 * @author gs_wenbing
 * @date 2020/3/4 13:15
 */
public class MainPresenter extends BasePresenter<IMainView> {

    void requestData() {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getBanner(),
                new BaseObserver<List<Banner>>(getView(), "加载中...") {
                    @Override
                    protected void onSuccess(List<Banner> banners) {
                        if (getView() != null) {
                            getView().showData(banners);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        if (getView() != null) {
                            getView().showError(ex.getMessage());
                        }
                    }

                }));
    }
}
