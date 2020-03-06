package com.wenbing.mvpdemo.module.tree;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.Tree;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:51
 */
public class TreePresenter extends BasePresenter<ITreeView> {

    public void requestData(final int action, int pageSize, int page) {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getTreeList(),
                new BaseObserver<List<Tree>>(getView(), false) {
                    @Override
                    protected void onSuccess(List<Tree> treeList) {
                        if (getView() != null) {
                            getView().showData(treeList, action);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {

                    }
                }));
    }
}
