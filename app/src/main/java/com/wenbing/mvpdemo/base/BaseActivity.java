package com.wenbing.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wenbing.mvpdemo.utils.MaterialDialogUtils;

/**
 * Activity 的所有基类
 *
 * @author gs_wenbing
 * @date 2020/3/4 10:15
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    private MaterialDialog mDialog;
    protected P mPresenter;

    protected abstract P createrPresenter();

    /**
     * 初始化布局文件
     *
     * @return 返回布局文件的xml
     */
    protected abstract int initLayoutID();

    /**
     * 初始化View控件
     */
    protected abstract void initViews();

    /**
     * 初始化View的事件
     */
    protected abstract void initViewListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(initLayoutID());
        initPresenter();
        initViews();
        initViewListener();
    }

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {
        mPresenter = createrPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
        }
    }


    @Override
    public void showLoading(String msg) {
        if (mDialog != null) {
            mDialog = mDialog.getBuilder().title(msg).build();
            mDialog.show();
        } else {
            MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(this, msg, true);
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }
    }
}
