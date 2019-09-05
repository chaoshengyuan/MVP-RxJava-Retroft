package com.wenbing.mvpdemo.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
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

    private static final String BASE_URL = "";

    private Retrofit mRetrofit;

    RetrofitFactory() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(getInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    private static Interceptor getInterceptor() {
        return new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();
                long startTime = System.currentTimeMillis();

                Response response = chain.proceed(chain.request());
                long endTime = System.currentTimeMillis();

                long duration = endTime - startTime;

                MediaType mediaType = response.body().contentType();
                String content = response.body().string();
                Log.e(TAG, "----------Request Start----------------");
                Log.e(TAG, "| " + request.toString() + request.headers().toString());
                Log.e(TAG, "| Response:" + content);
                Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
                return response.newBuilder()
                        .body(ResponseBody.create(mediaType, content))
                        .build();
            }
        };

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
