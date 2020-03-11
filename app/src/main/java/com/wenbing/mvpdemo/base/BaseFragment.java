package com.wenbing.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wenbing.mvpdemo.utils.MaterialDialogUtils;

/**
 * Fragment 的所有基类
 * @author gs_wenbing
 * @date 2020/3/4 10:16
 */
public abstract class BaseFragment<P extends BasePresenter> extends LazyFragment implements IBaseView{
    protected Context mContext;
    private MaterialDialog mDialog;
    public P mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initPresenter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    public void initPresenter(){
        mPresenter = createrPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }

    protected void initialize() {
        initViewsAndListener();
        initData();
    }

    @Override
    public void onDetach() {
        if(mPresenter!=null){
            mPresenter.onDetachView();
        }
        super.onDetach();
    }


    /**
     * 初始化布局文件
     * @return 返回布局文件的xml
     */
    protected abstract int initLayoutID();

    protected abstract P createrPresenter();
    /**
     * 初始化View控件和事件
     */
    protected abstract void initViewsAndListener();

    protected void initData(){

    }


    @Override
    public void showLoading(String msg) {
        if (mDialog != null) {
            mDialog = mDialog.getBuilder().title(msg).build();
            mDialog.show();
        } else {
            MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(mContext, msg, true);
            mDialog = builder.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showError(String msg) {

    }
}
