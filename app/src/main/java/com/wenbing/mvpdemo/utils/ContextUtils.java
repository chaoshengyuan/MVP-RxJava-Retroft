package com.wenbing.mvpdemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author: wenbing
 * @date: 2020/3/18 10:10
 */
public class ContextUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    public static void init(Context context) {
        ContextUtils.context = context;
    }

    public static Context getAppContext() {
        if (context == null) {
            throw new RuntimeException("Utils未在Application中初始化");
        }
        return context;
    }
}
