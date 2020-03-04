package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.beans.base.Response;
import com.wenbing.mvpdemo.retrofit.error.ApiException;
import com.wenbing.mvpdemo.retrofit.error.ExceptionEngine;
import com.wenbing.mvpdemo.retrofit.error.ServerException;
import com.wenbing.mvpdemo.utils.RxUtil;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * @author gs_wenbing
 * @date 2019/9/3 17:37
 */
public class ApiServer {

    private static ApiServer apiServer;
    private ApiInterface.API api;

    private ApiServer() {
        api = RetrofitFactory.getInstance().create(ApiInterface.API.class);
    }

    public static ApiServer getInstance() {
        if (apiServer == null) {
            synchronized (Object.class) {
                if (apiServer == null) {
                    apiServer = new ApiServer();
                }
            }
        }
        return apiServer;
    }

    public  ApiInterface.API getApi() {
        return api;
    }

    /**
     * 拦截固定格式的公共数据类型Response<T>,判断里面的状态码
     */
    private class ServerResultFunc<T> implements Function<Response<T>, T> {
        @Override
        public T apply(Response<T> response) {
            //对返回码进行判断，如果不是true，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
            if (response.getErrorCode() != 0) {
                //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                throw new ServerException(response.getErrorCode(), response.getErrorMsg());
            }
            //服务器请求数据成功，返回里面的数据实体
            return response.getData();
        }
    }

    private class ExceptionFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            ApiException exception = ExceptionEngine.handleException(throwable);
            return Observable.error(exception);
        }
    }

    public<T> DisposableObserver<T> toSubscribe(Observable<Response<T>> sourse, DisposableObserver<T> observer) {
        return sourse.map(new ServerResultFunc<T>())
                .onErrorResumeNext(new ExceptionFunc<T>())
                .compose(RxUtil.<T>background())
                .subscribeWith(observer);
    }

}
