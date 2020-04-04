package com.wenbing.mvpdemo.module.article;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.beans.base.BaseBean;
import com.wenbing.mvpdemo.event.CollectEvent;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:51
 */
public class ArticleDetailPresenter extends BasePresenter<IArticleDetailView> {
    public void collect(final int id) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().collect(id),
                new BaseObserver<BaseBean>(getView(),false) {
                    @Override
                    protected void onSuccess(BaseBean bean) {
                        if (getView() != null) {
                            new CollectEvent(true,id).post();
                            getView().collectSuccess();
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                    }
                }));
    }
    public void unCollect(final int id) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().uncollect(id),
                new BaseObserver<BaseBean>(getView(),false) {
                    @Override
                    protected void onSuccess(BaseBean bean) {
                        if (getView() != null) {
                            new CollectEvent(false,id).post();
                            getView().unCollectSuccess();
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                    }
                }));
    }

    public void requestData(String title,String author,String link) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().collect(title, author, link),
                new BaseObserver<ArticleBean>(getView(),false) {
                    @Override
                    protected void onSuccess(ArticleBean articleBean) {
                        if (getView() != null) {
                            getView().unCollectSuccess();
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                    }
                }));
    }
}
