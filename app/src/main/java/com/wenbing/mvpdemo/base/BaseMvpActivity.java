//package com.wenbing.mvpdemo.base;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//
//import com.wenbing.mvpdemo.mvp.BaseMvpPresenter;
//import com.wenbing.mvpdemo.mvp.BaseMvpView;
//
///**
// * @author gs_wenbing
// * @date 2019/9/3 15:11
// */
//public abstract class BaseMvpActivity<V extends BaseMvpView,P extends BaseMvpPresenter<V>> extends AppCompatActivity{
//    private P mPresenter;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(mPresenter==null){
//            mPresenter = createPresenter();
//        }
//
//        if(mPresenter == null){
//            throw new NullPointerException("Presenter is null");
//        }
//
//        if(this instanceof BaseMvpView){
//            getPresenter().attachView((V) this);
//        }else{
//            throw new ClassCastException(this.getLocalClassName() +" cannot be cast to BaseMvpView");
//        }
//    }
//
//    protected abstract P createPresenter();
//
//    public P getPresenter() {
//        return mPresenter;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(mPresenter!=null){
//            mPresenter.detachView();
//        }
//    }
//
//
//}
