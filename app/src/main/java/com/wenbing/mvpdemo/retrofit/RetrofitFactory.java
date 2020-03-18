package com.wenbing.mvpdemo.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wenbing.mvpdemo.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author gs_wenbing
 * @date 2019/9/4 16:33
 */
class RetrofitFactory {

    private static final String TAG = "ApiRetrofit";
    static final String BASE_URL = "https://www.wanandroid.com/";

    private Retrofit mRetrofit;

    private RetrofitFactory() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        LogUtils.setLevel(LogUtils.DEBUG);
        return new OkHttpClient
                .Builder()
                .addInterceptor(new CommonInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.i(message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    static RetrofitFactory getInstance() {
        // 返回一个单例对象
        return SingletonHolder.INSTANCE;
    }

    <T> T create(Class<T> service) {
        // 返回Retrofit创建的接口代理类
        return mRetrofit.create(service);
    }
}
