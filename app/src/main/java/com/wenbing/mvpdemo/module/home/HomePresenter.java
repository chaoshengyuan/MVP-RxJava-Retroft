package com.wenbing.mvpdemo.module.home;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.beans.BannerBean;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:40
 */
public class HomePresenter extends BasePresenter<IHomeView> {
    public void requestData(final int action, int pageSize, int page) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getArticleList(page),
                new BaseObserver<ArticleBean>(getView(),false) {
                    @Override
                    protected void onSuccess(ArticleBean ArticleBean) {
                        if (getView() != null) {
                            getView().showData(ArticleBean,action);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        if (getView() != null) {
                            getView().showData(null, RecyclerFragment.ACTION_LOAD_FAILED);
                        }
                    }
                }));
    }

    public void requestBanner() {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getBanner(),
                new BaseObserver<List<BannerBean>>(getView(),false) {
                    @Override
                    protected void onSuccess(List<BannerBean> bannerBeans) {
                        if (getView() != null) {
                            getView().showBanner(bannerBeans);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {

                    }
                }));
    }


}
