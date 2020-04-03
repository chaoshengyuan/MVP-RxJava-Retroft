package com.wenbing.mvpdemo.module.article;


import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.base.BasePresenter;
import com.wenbing.mvpdemo.widget.X5WebView;

/**
 * @author: wenbing
 * @date: 2020/3/8 13:27
 */
public class ArticleDetailActivity extends BaseActivity {
    TitleBar titleBar;
    X5WebView webView;

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected BasePresenter createrPresenter() {
        return null;
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initViews() {
        String webUrl = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        titleBar = (TitleBar) $(R.id.title_bar);
        titleBar.setTitle(title);
        webView = (X5WebView) $(R.id.article_webview);
        webView.loadUrl(webUrl);
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

            }
        });
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
