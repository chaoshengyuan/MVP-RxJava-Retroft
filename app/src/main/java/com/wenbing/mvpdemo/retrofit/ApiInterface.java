package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.bean.LoginResponse;
import com.wenbing.mvpdemo.bean.base.Request;
import com.wenbing.mvpdemo.bean.base.Response;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @author gs_wenbing
 * @date 2019/9/4 16:27
 */
interface ApiInterface {

    @POST
    Observable<Response<LoginResponse>> login(@Url String url,@Body Request request);
}
