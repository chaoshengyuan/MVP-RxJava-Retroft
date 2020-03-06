package com.wenbing.mvpdemo.module.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.beans.Article;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.HomeAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;

import java.util.ArrayList;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:39
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView,RecyclerFragment.RecyclerListener {
    private BaseRVAdapter<Article.DataDetailBean> mAdapter;
    RecyclerFragment<Article.DataDetailBean> recyclerFragment;

    @Override
    protected int initLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createrPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initViews() {
        mAdapter = new HomeAdapter(mContext, new ArrayList<Article.DataDetailBean>());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        recyclerFragment = RecyclerFragment.newInstance();
        fragmentTransaction.add(R.id.home_frame, recyclerFragment).commit();
        recyclerFragment.init(mAdapter, this);
    }

    @Override
    protected void initViewListener() {

    }

    @Override
    public void onRecyclerCreated(XRecyclerView recyclerView) {

    }

    @Override
    public void loadData(int action, int pageSize, int page) {
        mPresenter.requestData(action, pageSize, page);
    }

    @Override
    public void showData(Article article,int action) {
        recyclerFragment.loadCompleted(action, "", article.datas);
    }
}
