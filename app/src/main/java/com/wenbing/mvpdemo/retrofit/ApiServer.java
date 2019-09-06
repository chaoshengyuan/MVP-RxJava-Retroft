package com.wenbing.mvpdemo.retrofit;

import com.wenbing.mvpdemo.utils.RxUtil;
import com.wenbing.mvpdemo.bean.LoginRequest;
import com.wenbing.mvpdemo.bean.LoginResponse;
import com.wenbing.mvpdemo.bean.base.Request;
import com.wenbing.mvpdemo.bean.base.Response;
import com.wenbing.mvpdemo.retrofit.error.ApiException;
import com.wenbing.mvpdemo.retrofit.error.ExceptionEngine;
import com.wenbing.mvpdemo.retrofit.error.ServerException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * @author gs_wenbing
 * @date 2019/9/3 17:37
 */
public class ApiServer {

    private static ApiServer apiServer;
    private ApiInterface mInterface;

    private ApiServer() {
        mInterface = RetrofitFactory.getInstance().create(ApiInterface.class);
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

    /**
     * 拦截固定格式的公共数据类型Response<T>,判断里面的状态码
     */
    private class ServerResultFunc<T> implements Function<Response<T>, T> {
        @Override
        public T apply(Response<T> response) {
            //对返回码进行判断，如果不是true，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
            if (response.getResult() != 0) {
                //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
                throw new ServerException(response.getResult(), response.getErrMsg());
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

    public DisposableObserver<LoginResponse> login(Request<LoginRequest> Request,DisposableObserver<LoginResponse> observer) {
        return mInterface.login(URLContants.LOGIN,Request)
                .map(new ServerResultFunc<LoginResponse>())
                .onErrorResumeNext(new ExceptionFunc<LoginResponse>())
                .compose(RxUtil.<LoginResponse>background())
                .subscribeWith(observer);
    }

}
