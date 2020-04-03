package com.wenbing.mvpdemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.hjq.bar.ITitleBarStyle;
import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;
import com.wenbing.mvpdemo.common.WebviewTBS;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author: wenbing
 * @date: 2020/3/18 10:11
 */
public class MyAPP extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Application application = null;

    private static PersistentCookieJar mCookieJar = null;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        TitleBar.initStyle(new TitleBarLightStyle(getApplicationContext()));
        //初始化百度TBS
        WebviewTBS.getAPIWebview().initTbs(getApplicationContext());
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }

    public static Application getApp() {
        if (application == null) {
            throw new NullPointerException("App is not registered in the manifest");
        } else {
            return application;
        }
    }

    public static Context getAppContext() {
        return getApp().getApplicationContext();
    }


    public static PersistentCookieJar getCookieJar() {
        if (mCookieJar == null) {
            mCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getAppContext()));
        }
        return mCookieJar;
    }


}
