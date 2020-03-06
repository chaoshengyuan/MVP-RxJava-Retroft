package com.wenbing.mvpdemo.widget.bottomtab;

import com.wenbing.mvpdemo.base.BaseFragment;

public class TabItem {
    /**
     * icon
     */
    public int imageResId;
    /**
     * 文本
     */
    public int lableResId;

    public int index;

    public Class<? extends BaseFragment>tagFragmentClz;


    public TabItem(int imageResId, int lableResId) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
    }
    public TabItem(int imageResId, int lableResId,int index, Class<? extends BaseFragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
        this.index = index;
    }
}