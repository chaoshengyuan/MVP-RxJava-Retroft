package com.wenbing.mvpdemo.module.project;

import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.beans.ProjectTree;
import com.wenbing.mvpdemo.retrofit.BaseObserver;
import com.wenbing.mvpdemo.retrofit.error.ApiException;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:48
 */
public class ProjectPresenter extends BasePresenter<IProjectView> {

    public void requestData() {
        if (getView() == null) {
            return;
        }
        addDisposable(mApiServer.toSubscribe(mApiServer.getApi().getProjectTreeList(),
                new BaseObserver<List<ProjectTree>>(getView(), false) {
                    @Override
                    protected void onSuccess(List<ProjectTree> treeList) {
                        if (getView() != null) {
                            getView().showTabData(treeList);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        if (getView() != null) {

                        }
                    }
                }));
    }
}
