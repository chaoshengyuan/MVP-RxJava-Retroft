package com.wenbing.mvpdemo.base;

import com.wenbing.mvpdemo.retrofit.ApiServer;

import java.lang.ref.SoftReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Presenter 的所有基类
 *
 * @author gs_wenbing
 * @date 2020/3/4 10:16
 */
public class BasePresenter<V extends IBaseView> {
    //用于及时取消订阅，以防止内存泄漏
    private CompositeDisposable mDisposable;

    protected ApiServer mApiServer = ApiServer.getInstance();

    private SoftReference <V> mReferenceView;

    /**
     * activity 创建的时关联presenter
     * @param view
     */
    public void onAttachView(V view) {
        this.mReferenceView = new SoftReference<>(view);
    }

    /**
     * activity 关闭时解除绑定,防止内存泄漏
     */
    public void onDetachView() {
        this.mReferenceView.clear();
        this.mReferenceView = null;
        removeDisposable();
    }

    public V getView() {
        return this.mReferenceView.get();
    }

    protected void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.add(disposable);
        }
    }

    protected void removeDisposable() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
