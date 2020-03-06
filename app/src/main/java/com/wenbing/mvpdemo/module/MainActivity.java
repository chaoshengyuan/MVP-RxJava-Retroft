package com.wenbing.mvpdemo.module;

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
        mTabLayout = findViewById(R.id.tab_layout);
        ArrayList<TabItem> tabs = new ArrayList<>();
        tabs.add(new TabItem(R.drawable.selector_tab_home, R.string.tab_home,0, HomeFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_tree, R.string.tab_tree,1, TreeFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_pro, R.string.tab_project,2, ProjectFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_me, R.string.tab_me,3, MeFragment.class));
        mTabLayout.initData(tabs, this);
//        mTabLayout.setCurrentTab(0);
    }

    @Override
    protected void initViewListener() {
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        try {
            mTabLayout.setCurrentTab(tabItem.index);
            BaseFragment fragment= tabItem.tagFragmentClz.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commitAllowingStateLoss();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
