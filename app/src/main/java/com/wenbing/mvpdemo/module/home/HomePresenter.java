package com.wenbing.mvpdemo.module.home;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.Article;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

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
                new BaseObserver<Article>(getView(),false) {
                    @Override
                    protected void onSuccess(Article article) {
                        if (getView() != null) {
                            getView().showData(article,action);
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
}
