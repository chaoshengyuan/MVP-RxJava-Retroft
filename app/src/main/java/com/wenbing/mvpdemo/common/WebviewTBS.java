package com.wenbing.mvpdemo.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.WindowManager;

import com.tencent.smtt.sdk.QbSdk;
import com.wenbing.mvpdemo.utils.LogUtils;

/**
 * @author: wenbing
 * @date: 2020/4/3 11:21
 */
public class WebviewTBS {
    private static WebviewTBS mWebviewTBS;

    public static WebviewTBS getAPIWebview() {
        if (mWebviewTBS == null) {
            synchronized (WebviewTBS.class) {
                if (mWebviewTBS == null) {
                    mWebviewTBS = new WebviewTBS();
                }
            }
        }
        return mWebviewTBS;
    }

    public void initTbs(Context context) {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                LogUtils.i("x5", "initX5Environment->onCoreInitFinished");
            }
            @Override
            public void onCoreInitFinished() {
                LogUtils.i("x5", "initX5Environment->onCoreInitFinished");
            }
        };
        QbSdk.initX5Environment(context,cb);
    }
    public void initTBSActivity(Activity ac){   //二次封装
        if (ac!=null){
            ac.getWindow().setFormat(PixelFormat.TRANSLUCENT);
            ac.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }

    }
}