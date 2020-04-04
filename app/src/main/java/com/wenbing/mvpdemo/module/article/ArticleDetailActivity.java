package com.wenbing.mvpdemo.module.article;


import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.widget.X5WebView;

/**
 * @author: wenbing
 * @date: 2020/3/8 13:27
 */
public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements IArticleDetailView {
    TitleBar titleBar;
    X5WebView webView;

    ArticleBean.DataBean ArticleBean;

    public static void start(Context context, ArticleBean.DataBean dataBean) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    protected ArticleDetailPresenter createrPresenter() {
        return new ArticleDetailPresenter();
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initViews() {
        ArticleBean = getIntent().getParcelableExtra("dataBean");
        titleBar = $(R.id.title_bar);
        titleBar.setTitle(ArticleBean.getTitle());
        webView = $(R.id.article_webview);
        webView.loadUrl(ArticleBean.getLink());

        if(ArticleBean.isCollect()){
            titleBar.setRightIcon(R.drawable.collected1);
        }
    }

    @Override
    protected void initListener() {
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
//                    mPresenter.requestData(ArticleBean.getTitle(), ArticleBean.getAuthor(), ArticleBean.getLink());
                if(!ArticleBean.isCollect()){
                    mPresenter.collect(ArticleBean.getId());
                }else{
                    mPresenter.unCollect(ArticleBean.getId());

                }
            }
        });
    }


    @Override
    public void collectSuccess() {
        ArticleBean.setCollect(true);
        titleBar.setRightIcon(R.drawable.collected1);
    }

    @Override
    public void unCollectSuccess() {
        ArticleBean.setCollect(false);
        titleBar.setRightIcon(R.drawable.un_collected1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(webView!=null){
            webView.onResume();
            webView.getSettings().setLightTouchEnabled(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(webView!=null){
            webView.onPause();
            webView.getSettings().setLightTouchEnabled(false);
        }
    }

    @Override
    public void onDestroy() {
        if (this.webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }

}
