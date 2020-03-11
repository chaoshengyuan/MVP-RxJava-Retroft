package com.wenbing.mvpdemo.module.me;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:47
 */
public class MeFragment extends BaseFragment<MePresenter> implements IMeView {
    @Override
    protected int initLayoutID() {
        return R.layout.fragment_me;
    }

    @Override
    protected MePresenter createrPresenter() {
        return new MePresenter();
    }

    @Override
    protected void initViewsAndListener() {

    }

    @Override
    protected void initData() {

    }
}
