package com.wenbing.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wenbing.mvpdemo.utils.MaterialDialogUtils;

/**
 * Fragment 的所有基类
 * @author gs_wenbing
 * @date 2020/3/4 10:16
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView{
    protected Context mContext;
    protected Bundle mBundle;
    protected View mView;
    private MaterialDialog mDialog;
    public P mPresenter;
    /**
     * 恢复数据
     *
     * @param outState bundle
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
    }
    /**
     * 绑定activity
     *
     * @param context context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * 运行在onAttach之后，可以接收别人传递过来的参数，实例化对象
     * 可以解决返回的时候页面空白的bug
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
       initPresenter();
    }

    /**
     * 初始化presenter
     */
    public void initPresenter(){
        mPresenter = createrPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }
    /**
     * 运行在onCreate之后，生成View视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(initLayoutID(), container, false);
        initViews();
        initViewListener();
        return mView;
    }
    protected <T extends View> T $(int id) {
        return (T) mView.findViewById(id);
    }
    /**
     * fragment进行回退
     * 类似于activity的OnBackPress
     */
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onDetach() {
        if(mPresenter!=null){
            mPresenter.onDetachView();
        }
        super.onDetach();
    }


    /**
     * 得到context
     *
     * @return context
     */
    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 得到bundle
     *
     * @return bundle
     */
    public Bundle getBundle() {
        return mBundle;
    }

    /**
     * 得到fragment
     *
     * @return fragment
     */
    public Fragment getFragment() {
        return this;
    }

    /**
     * 初始化布局文件
     * @return 返回布局文件的xml
     */
    protected abstract int initLayoutID();

    protected abstract P createrPresenter();
    /**
     * 初始化View控件
     */
    protected abstract void initViews();
    /**
     * 初始化View的事件
     */
    protected abstract void initViewListener();

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
