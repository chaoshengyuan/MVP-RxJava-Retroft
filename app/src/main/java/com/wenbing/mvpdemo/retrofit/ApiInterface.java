package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.beans.Banner;
import com.wenbing.mvpdemo.beans.base.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author gs_wenbing
 * @date 2019/9/4 16:27
 */
class ApiInterface {
    public interface API{
        //首页banner
        @GET("banner/json")
       public Observable<Response<List<Banner>>> getBanner();
    }

}
