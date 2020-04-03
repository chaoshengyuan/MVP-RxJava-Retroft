package com.wenbing.mvpdemo.module.article;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
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

    private TitleBar titleBar;

    public static void start(Context context, int id, String title) {
        Intent intent = new Intent(context, ArticleActvity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

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
        String title = getIntent().getStringExtra("title");
        titleBar = $(R.id.title_bar);
        titleBar.setTitle(title);

    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickLinsener(this);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
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
        ArticleDetailActivity.start(this,dataBean.getLink(),dataBean.getTitle());
    }
}
