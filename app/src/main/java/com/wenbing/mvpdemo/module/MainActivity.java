package com.wenbing.mvpdemo.module;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.module.home.HomeFragment;
import com.wenbing.mvpdemo.module.me.MeFragment;
import com.wenbing.mvpdemo.module.project.ProjectFragment;
import com.wenbing.mvpdemo.module.tree.TreeFragment;
import com.wenbing.mvpdemo.widget.bottomtab.TabItem;
import com.wenbing.mvpdemo.widget.bottomtab.TabLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements TabLayout.OnTabClickListener {

    private TabLayout mTabLayout;
    private BaseFragment mHomeFragment, mTreeFragment, mProjectFragment, mMeFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected BasePresenter createrPresenter() {
        return null;
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mTabLayout = findViewById(R.id.tab_layout);
        ArrayList<TabItem> tabs = new ArrayList<>();
        tabs.add(new TabItem(R.drawable.selector_tab_home, R.string.tab_home, 0, HomeFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_tree, R.string.tab_tree, 1, TreeFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_pro, R.string.tab_project, 2, ProjectFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_me, R.string.tab_me, 3, MeFragment.class));
        mTabLayout.initData(tabs, this);

    }

    @Override
    protected void initViewListener() {
    }


    @Override
    public void onTabClick(TabItem tabItem) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        try {
            mTabLayout.setCurrentTab(tabItem.index);
            switch (tabItem.index) {
                case 0:
                    if (mHomeFragment == null) {
                        mHomeFragment = tabItem.tagFragmentClz.newInstance();
                        transaction.add(R.id.fragment, mHomeFragment);
                    } else {
                        transaction.show(mHomeFragment);
                    }
                    transaction.commit();
                    break;
                case 1:
                    if (mTreeFragment == null) {
                        mTreeFragment = tabItem.tagFragmentClz.newInstance();
                        transaction.add(R.id.fragment, mTreeFragment);
                    } else {
                        transaction.show(mTreeFragment);
                    }
                    transaction.commit();
                    break;
                case 2:
                    if (mProjectFragment == null) {
                        mProjectFragment = tabItem.tagFragmentClz.newInstance();
                        transaction.add(R.id.fragment, mProjectFragment);
                    } else {
                        transaction.show(mProjectFragment);
                    }
                    transaction.commit();
                    break;
                case 3:
                    if (mMeFragment == null) {
                        mMeFragment = tabItem.tagFragmentClz.newInstance();
                        transaction.add(R.id.fragment, mMeFragment);
                    } else {
                        transaction.show(mMeFragment);
                    }
                    transaction.commit();
                    break;
                default:
                    break;

            }

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mTreeFragment != null) {
            transaction.hide(mTreeFragment);
        }
        if (mProjectFragment != null) {
            transaction.hide(mProjectFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }
}
