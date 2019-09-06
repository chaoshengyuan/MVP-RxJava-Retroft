package com.wenbing.mvpdemo.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gs_wenbing
 * @date 2019/9/5 13:19
 */
public class RxUtil {
    /**
     * 后台线程执行同步，主线程执行异步操作
     *
     * @param <T> 数据类型
     * @return 观察者
     */
    public static <T> ObservableTransformer<T, T> background() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
