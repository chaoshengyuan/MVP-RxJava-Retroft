package com.wenbing.mvpdemo.module.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/10 15:51
 */
public class MultiFragmentPagerAdapter<E, F extends Fragment> extends FragmentStatePagerAdapter {

    private FragmentCreator<E, F> mCreater;
    private List<E> mDataList;

    public MultiFragmentPagerAdapter(FragmentManager fm, FragmentCreator<E, F> creater) {
        super(fm);
        mCreater = creater;
    }

    public void setData(List<E> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return mCreater.create(mDataList.get(i), i);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCreater.getTitle(mDataList.get(position));
    }

    public interface FragmentCreator<E, F> {
        F create(E data, int pos);

        String getTitle(E data);
    }
}
