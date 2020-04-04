package com.wenbing.mvpdemo.module.home;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.beans.BannerBean;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:40
 */
public interface IHomeView extends IBaseView {
    void showData(ArticleBean article, int action);
    void showBanner(List<BannerBean> bannerBeans);
}
