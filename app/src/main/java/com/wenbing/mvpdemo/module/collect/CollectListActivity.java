/**
 * Copyright (C), 2015-2020, XXX有限公司
 * Author: zwb
 * Date: 2020/4/4 15:03
 * Description: 收藏列表
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.wenbing.mvpdemo.module.collect;

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
import com.wenbing.mvpdemo.base.IBaseView;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.event.CollectEvent;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.ArticleAdapter;
import com.wenbing.mvpdemo.module.adapter.CollectAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.article.ArticleDetailActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zwb
 * @Date: 2020/4/4 15:03
 */
public class CollectListActivity extends BaseActivity<CollectListPresenter> implements ICollectListView, RecyclerFragment.RecyclerListener, BaseRVAdapter.OnItemClickLinsener {
    private BaseRVAdapter<ArticleBean.DataBean> mAdapter;
    RecyclerFragment<ArticleBean.DataBean> recyclerFragment;
    private TitleBar titleBar;

    public static void start(Context context) {
        Intent intent = new Intent(context, CollectListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected CollectListPresenter createrPresenter() {
        return new CollectListPresenter();
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initViews() {
        mAdapter = new CollectAdapter(this, new ArrayList<ArticleBean.DataBean>());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        recyclerFragment = RecyclerFragment.newInstance();
        fragmentTransaction.add(R.id.collect_frame, recyclerFragment).commit();
        recyclerFragment.init(mAdapter, this);
        titleBar = $(R.id.title_bar);
        titleBar.setTitle("收藏列表");
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
        mPresenter.requestData(action, pageSize, page);
    }

    @Override
    public void showData(ArticleBean article, int action) {
        recyclerFragment.loadCompleted(action, "", article == null ? null : article.getDatas());
    }

    @Override
    public void onItemClick(BaseRVAdapter baseAdapter, int position) {
        ArticleBean.DataBean dataBean = mAdapter.getBeans().get(position - 1);
        ArticleDetailActivity.start(this,dataBean);
    }
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectedAction(CollectEvent collectEvent){
        List<ArticleBean.DataBean> dataBeans  = mAdapter.getBeans();
        for (int i = 0; i < dataBeans.size(); i++) {
            if(dataBeans.get(i).getId()==collectEvent.getArticleID()){
                dataBeans.get(i).setCollect(collectEvent.isCollected());
                mAdapter.notifyItemChanged(i+ 1);
                break;
            }
        }
    }
}