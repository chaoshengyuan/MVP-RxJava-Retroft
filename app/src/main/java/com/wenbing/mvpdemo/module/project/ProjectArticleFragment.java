package com.wenbing.mvpdemo.module.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.beans.ProjectArticle;
import com.wenbing.mvpdemo.beans.ProjectTree;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.ProjectArticleAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.article.ArticleDetailActivity;
import com.wenbing.mvpdemo.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/10 11:34
 */
public class ProjectArticleFragment extends BaseFragment<ProjectArtcilePresenter> implements IProjectActicleView, BaseRVAdapter.OnItemClickLinsener {

    private BaseRVAdapter<ProjectArticle.DatasBean> mAdapter;
    protected XRecyclerView mRecyclerView;
    private LoadingLayout rootView;
    private ProjectTree mProjectTree;
    private int mCurrentPage = 0;
    public  int mPageSize = 15;

    public static ProjectArticleFragment newInstance(ProjectTree projectTree) {
        Bundle args = new Bundle();
        ProjectArticleFragment fragment = new ProjectArticleFragment();
        args.putParcelable("projectTree", projectTree);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int initLayoutID() {
        return R.layout.frament_tab;
    }

    @Override
    protected ProjectArtcilePresenter createrPresenter() {
        return new ProjectArtcilePresenter();
    }

    @Override
    protected void initViewsAndListener() {
        mAdapter = new ProjectArticleAdapter(mContext, new ArrayList<ProjectArticle.DatasBean>());
        rootView = $(R.id.root_view);
        mRecyclerView = $(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.reset();
                loadData(RecyclerFragment.ACTION_REFRESH,  mCurrentPage = 0);
            }

            @Override
            public void onLoadMore() {
                loadData(RecyclerFragment.ACTION_LOAD_MORE, mCurrentPage = mCurrentPage + 1);

            }
        });
        rootView.setReloadClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.reset();
                loadData(RecyclerFragment.ACTION_DEFAULT,mCurrentPage = 0);
            }
        });
        mAdapter.setOnItemClickLinsener(this);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mProjectTree = bundle.getParcelable("projectTree");
        }
    }

    @Override
    protected void onVisible(boolean isFirstVisible) {
        super.onVisible(isFirstVisible);
        if(isFirstVisible){
            if(mProjectTree!=null){
                rootView.showLoading("数据加载中...");
                loadData(RecyclerFragment.ACTION_DEFAULT,mCurrentPage = 0);
            }else{
                Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadData(int action, int page) {
        mPresenter.requestData(action, page, mProjectTree.getId());
    }

    @Override
    public void showData(ProjectArticle projectArticle, int action) {
        loadCompleted(action, projectArticle == null ? null : projectArticle.getDatas());
    }
    public void loadCompleted(int action, List<ProjectArticle.DatasBean> list) {
        if (getActivity() == null) {
            return;
        }
        if (list != null) {
            if (action == RecyclerFragment.ACTION_LOAD_MORE) {
                //加载更多
                mAdapter.addAll(list, false);
                if (list.size() < mPageSize) {
                    //加载的数据少于一页时
                    mRecyclerView.noMoreLoading();
                }
            } else {
                //首次加载、下拉刷新
                mAdapter.addAll(list, true);
            }
        }
        if (action == RecyclerFragment.ACTION_LOAD_FAILED) {
            rootView.showFailed("网络不好哦，请稍后再试");
        } else {
            if (mAdapter.getBeans().size() == 0) {
                rootView.showEmpty();
            } else {
                rootView.showContent();
            }
        }
        mRecyclerView.refreshComplete();
        mRecyclerView.loadMoreComplete();
    }

    @Override
    public void onItemClick(BaseRVAdapter baseAdapter, int position) {
        ProjectArticle.DatasBean datasBean = mAdapter.getBeans().get(position - 1);
        ArticleDetailActivity.start(mContext,datasBean.getLink(),datasBean.getTitle());
    }

}
