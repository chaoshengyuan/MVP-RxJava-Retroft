package com.wenbing.mvpdemo.module.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.beans.LoginBean;
import com.wenbing.mvpdemo.event.LoginEvent;
import com.wenbing.mvpdemo.widget.InputView;

/**
 * @author: wenbing
 * @date: 2020/3/16 15:50
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView, View.OnClickListener {
    InputView etUsername,etPassword;
    Button btnLogin;

    @Override
    protected LoginPresenter createrPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        etUsername = $(R.id.et_username);
        etPassword = $(R.id.et_password);
        etUsername.setText("gs_wenbing");
        etPassword.setText("zwb19880313");
        btnLogin = $(R.id.btn_login);

    }
    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(etUsername.getText()==null || TextUtils.isEmpty(etUsername.getText().toString())){
                    return;
                }
                if(etPassword.getText()==null || TextUtils.isEmpty(etPassword.getText().toString())){
                    return;
                }
                mPresenter.requestData(etUsername.getText().toString(),etPassword.getText().toString());
                break;
        }
    }

    @Override
    public void loginSusscee(LoginBean loginBean) {
        new LoginEvent(true).post();
        finish();
    }

    @Override
    public void loginFailed(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}
