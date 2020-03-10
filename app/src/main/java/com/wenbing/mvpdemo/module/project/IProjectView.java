package com.wenbing.mvpdemo.module.project;

import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.ProjectTree;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:49
 */
public interface IProjectView extends IBaseView {

    void showTabData(List<ProjectTree> projectTreeList);
}
