package com.wenbing.mvpdemo.module.project;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:48
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements IProjectView{
    @Override
    protected int initLayoutID() {
        return  R.layout.fragment_project;
    }

    @Override
    protected ProjectPresenter createrPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initViewListener() {

    }
}
