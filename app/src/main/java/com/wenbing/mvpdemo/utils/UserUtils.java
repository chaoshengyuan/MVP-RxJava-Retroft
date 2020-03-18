package com.wenbing.mvpdemo.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.wenbing.mvpdemo.beans.LoginBean;

/**
 * @author: wenbing
 * @date: 2020/3/18 10:17
 */
public class UserUtils {
    private static final String KEY_USER = "KEY_USER";

    private LoginBean mLoginBean;

    private static class Holder {
        private static final UserUtils INSTANCE = new UserUtils();
    }

    public static UserUtils getInstance() {
        return Holder.INSTANCE;
    }

    public void login(LoginBean loginBean) {
        mLoginBean = loginBean;
        SPUtils.getInstance().save(KEY_USER,new Gson().toJson(loginBean));
    }

    private void logout(){
        mLoginBean = null;
        SPUtils.getInstance().clear();
    }
    public LoginBean getLoginBean() {
        if (mLoginBean == null) {
            String json = SPUtils.getInstance().get(KEY_USER, "");
            if (!TextUtils.isEmpty(json)) {
                try {
                    mLoginBean = new Gson().fromJson(json, LoginBean.class);
                } catch (Exception ignore) {
                }
            }
        }
        return mLoginBean;
    }

    public boolean isLogin(){
        LoginBean loginBean = getLoginBean();
        if (loginBean == null) {
            return false;
        }
        return loginBean.getId() > 0;
    }
}
