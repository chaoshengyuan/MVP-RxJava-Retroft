package com.wenbing.mvpdemo.module.project;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:48
 */
public class ProjectArtcilePresenter extends BasePresenter<IProjectActicleView> {

    public void requestData(final int action,int page,int id) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getProjectList(page,id),
                new BaseObserver<ArticleBean>(getView(), false) {
                    @Override
                    protected void onSuccess(ArticleBean articleBean) {
                        if (getView() != null) {
                            getView().showData(articleBean, action);
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
