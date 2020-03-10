package com.wenbing.mvpdemo.module.project;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.beans.ProjectTree;
import com.wenbing.mvpdemo.module.adapter.MultiFragmentPagerAdapter;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:48
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements IProjectView {
    private MultiFragmentPagerAdapter<ProjectTree,ProjectArticleFragment> fragmentAdapter;

    @Override
    protected int initLayoutID() {
        return R.layout.fragment_project;
    }

    @Override
    protected ProjectPresenter createrPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void initViews() {
        ViewPager mViewPager = $(R.id.page);
        TabLayout tabLayout = $(R.id.tab_layout);
        fragmentAdapter = new MultiFragmentPagerAdapter<>(getChildFragmentManager(),
                new MultiFragmentPagerAdapter.FragmentCreator<ProjectTree, ProjectArticleFragment>() {
                    @Override
                    public ProjectArticleFragment create(ProjectTree data, int pos) {
                        return ProjectArticleFragment.newInstance(data);
                    }

                    @Override
                    public String getTitle(ProjectTree data) {
                        return data.getName();
                    }
                });
        mViewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(mViewPager);//与ViewPage建立关系
        loadData();
    }
    @Override
    protected void initViewListener() {
    }


    private void loadData() {
        mPresenter.requestData();
    }

    @Override
    public void showTabData(List<ProjectTree> projectTreeList) {
        fragmentAdapter.setData(projectTreeList);
//        fragmentAdapter.notifyDataSetChanged();
    }

}
