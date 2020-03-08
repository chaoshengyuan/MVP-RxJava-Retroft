package com.wenbing.mvpdemo.module.article;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.Article;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:50
 */
public interface IArticleView extends IBaseView {
    void showData(Article article, int action);
}
