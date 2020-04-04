/**
 * Copyright (C), 2015-2020, XXX有限公司
 * Author: zwb
 * Date: 2020/4/4 15:04
 * Description: 收藏
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.wenbing.mvpdemo.module.collect;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

/**
 * @Author: zwb
 * @Date: 2020/4/4 15:04
 */
public class CollectListPresenter extends BasePresenter<ICollectListView> {
    public void requestData(final int action, int pageSize, int page) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getCollectList(page),
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