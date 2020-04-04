package com.wenbing.mvpdemo.module.article;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:51
 */
public class ArticlePresenter extends BasePresenter<IArticleView> {
    public void requestData(final int action, int pageSize, int page,int pID) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getTreeArticleList(page, pID),
                new BaseObserver<ArticleBean>(getView(),false) {
                    @Override
                    protected void onSuccess(ArticleBean ArticleBean) {
                        if (getView() != null) {
                            getView().showData(ArticleBean, action);
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
