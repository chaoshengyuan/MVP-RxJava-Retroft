package com.wenbing.mvpdemo.module.article;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.base.BasePresenter;

/**
 * @author: wenbing
 * @date: 2020/3/8 13:27
 */
public class ArticleDetailActivity extends BaseActivity {
    WebView webView;
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
      String url = getIntent().getStringExtra("url");
        webView = findViewById(R.id.article_webview);
        //WebView加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebView中打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候是控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知
        });
        //启用支持Javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //页面加载
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress   1-100之间的整数
                if (newProgress == 100) {
                    //页面加载完成，关闭ProgressDialog
                } else {
                    //网页正在加载，打开ProgressDialog
                }
            }


        });
    }

    @Override
    protected void initViewListener() {

    }
}
