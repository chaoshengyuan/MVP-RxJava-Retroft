package com.wenbing.mvpdemo.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author gs_wenbing
 * @date 2019/9/5 15:58
 */
public class CommonInterceptor implements Interceptor {

    private static final String TAG = "ApiRetrofit";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Content-Type","application/json");
        builder.addHeader("Accept","application/json");
        builder.method(request.method(),request.body());

//        long startTime = System.currentTimeMillis();
//        Response response = chain.proceed(builder.build());
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime;
//        MediaType mediaType = response.body().contentType();
//        String content = response.body().string();
//        Log.e(TAG, "----------Request Start----------------");
//        Log.e(TAG, "| " + request.toString() + request.headers().toString());
//        Log.e(TAG, "| Response:" + content);
//        Log.e(TAG, "----------Request End:" + duration + "毫秒----------");

        return chain.proceed(builder.build());
    }
}
