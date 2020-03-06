package com.wenbing.mvpdemo.module.tree;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.beans.Tree;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.TreeAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:51
 */
public class TreeFragment extends BaseFragment<TreePresenter> implements ITreeView , RecyclerFragment.RecyclerListener {
    private BaseRVAdapter<Tree> mAdapter;
    RecyclerFragment<Tree> recyclerFragment;

    @Override
    protected int initLayoutID() {
        return R.layout.fragment_tree;
    }

    @Override
    protected TreePresenter createrPresenter() {
        return new TreePresenter();
    }

    @Override
    protected void initViews() {
        mAdapter = new TreeAdapter(mContext, new ArrayList<Tree>());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        recyclerFragment = RecyclerFragment.newInstance();
        fragmentTransaction.add(R.id.tree_frame, recyclerFragment).commit();
        recyclerFragment.init(mAdapter, this);
    }

    @Override
    protected void initViewListener() {

    }

    @Override
    public void onRecyclerCreated(XRecyclerView recyclerView) {
        recyclerFragment.setLoadingEnable(false);
        recyclerFragment.setRefreshEnable(false);
    }

    @Override
    public void loadData(int action, int pageSize, int page) {
        mPresenter.requestData(action, pageSize, page);
    }

    @Override
    public void showData(List<Tree> treeList,int action) {
        recyclerFragment.loadCompleted(action,"",treeList);
    }
}