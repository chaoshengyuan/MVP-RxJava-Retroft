package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.beans.Article;
import com.wenbing.mvpdemo.beans.BannerBean;
import com.wenbing.mvpdemo.beans.Tree;
import com.wenbing.mvpdemo.beans.base.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author gs_wenbing
 * @date 2019/9/4 16:27
 */
class ApiInterface {
    public interface API {
        //-----------------------【首页相关】----------------------

        /**
         * @return 首页banner
         */
        @GET("banner/json")
        public Observable<Response<List<BannerBean>>> getBanner();

        /**
         * @param page 页码
         * @return  首页文章列表
         */
        @GET("article/list/{page}/json")
        Observable<Response<Article>> getArticleList(@Path("page") Integer page);

        /**
         * @return  体系数据
         */
        @GET("tree/json")
        Observable<Response<List<Tree>>> getTreeList();

        @GET("article/list/{page}/json")
        Observable<Response<Article>> getTreeArticleList(@Path("page") int page, @Query("cid") int id);
    }

}
