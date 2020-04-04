package com.wenbing.mvpdemo.module.tree;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.TreeBean;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:51
 */
public interface ITreeView extends IBaseView {
    void showData(List<TreeBean> TreeList, int action);
}
