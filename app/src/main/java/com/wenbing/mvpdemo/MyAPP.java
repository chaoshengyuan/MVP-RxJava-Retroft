package com.wenbing.mvpdemo;

import android.app.Application;

import com.wenbing.mvpdemo.utils.ContextUtils;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author: wenbing
 * @date: 2020/3/18 10:11
 */
public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.init(this);

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }
}
