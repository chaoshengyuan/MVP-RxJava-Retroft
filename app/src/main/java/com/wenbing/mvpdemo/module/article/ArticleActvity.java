package com.wenbing.mvpdemo.module.article;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.beans.Article;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.ArticleAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;

import java.util.ArrayList;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:50
 */
public class ArticleActvity extends BaseActivity<ArticlePresenter> implements IArticleView, RecyclerFragment.RecyclerListener, BaseRVAdapter.OnItemClickLinsener {
    private BaseRVAdapter<Article.DataBean> mAdapter;
    RecyclerFragment<Article.DataBean> recyclerFragment;
    private int pID;

    @Override
    protected ArticlePresenter createrPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_article;
    }

    @Override
    protected void initViews() {
        mAdapter = new ArticleAdapter(this, new ArrayList<Article.DataBean>());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        recyclerFragment = RecyclerFragment.newInstance();
        fragmentTransaction.add(R.id.article_frame, recyclerFragment).commit();
        recyclerFragment.init(mAdapter, this);

        pID = getIntent().getIntExtra("id", -1);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickLinsener(this);
    }

    @Override
    public void onRecyclerCreated(XRecyclerView recyclerView) {

    }

    @Override
    public void loadData(int action, int pageSize, int page) {
        mPresenter.requestData(action, pageSize, page, pID);
    }

    @Override
    public void showData(Article article, int action) {
        recyclerFragment.loadCompleted(action, "", article == null ? null : article.getDatas());
    }

    @Override
    public void onItemClick(BaseRVAdapter baseAdapter, int position) {
        Article.DataBean dataBean = mAdapter.getBeans().get(position - 1);
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra("url", dataBean.getLink());
        startActivity(intent);
    }
}
