package com.wenbing.mvpdemo.module.project;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.ProjectArticle;

/**
 * @author: wenbing
 * @date: 2020/3/10 13:09
 */
public interface IProjectActicleView extends IBaseView {

    void showData(ProjectArticle projectArticle, int action);
}
