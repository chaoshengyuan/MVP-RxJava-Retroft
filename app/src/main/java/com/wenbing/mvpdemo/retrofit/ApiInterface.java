package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.bean.LoginResponse;
import com.wenbing.mvpdemo.bean.base.Request;
import com.wenbing.mvpdemo.bean.base.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author gs_wenbing
 * @date 2019/9/4 16:27
 */
interface ApiInterface {

    @GET
    Observable<Response<LoginResponse>> login(@Url String url);
}
