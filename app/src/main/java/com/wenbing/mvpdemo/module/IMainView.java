package com.wenbing.mvpdemo.module;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.Banner;

import java.util.List;

/**
 * @author gs_wenbing
 * @date 2020/3/4 13:17
 */
public interface IMainView extends IBaseView {
    void showData(List<Banner> banners);
}
