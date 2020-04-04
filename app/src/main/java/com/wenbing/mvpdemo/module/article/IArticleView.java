package com.wenbing.mvpdemo.module.article;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.ArticleBean;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:50
 */
public interface IArticleView extends IBaseView {
    void showData(ArticleBean article, int action);
}
