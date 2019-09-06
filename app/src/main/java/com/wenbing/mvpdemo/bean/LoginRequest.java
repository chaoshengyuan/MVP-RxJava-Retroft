package com.wenbing.mvpdemo.bean;

/**
 * @author gs_wenbing
 * @date 2019/9/6 9:09
 */
public class LoginRequest {
    private String UserNo;
    private String Password;

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String userNo) {
        UserNo = userNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
