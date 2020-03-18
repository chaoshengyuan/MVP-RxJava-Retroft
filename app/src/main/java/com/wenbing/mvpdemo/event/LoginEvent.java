package com.wenbing.mvpdemo.event;

/**
 * @author: wenbing
 * @date: 2020/3/18 11:15
 */
public class LoginEvent extends BaseEvent{
    private boolean login;

    public LoginEvent(boolean login) {
        this.login = login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isLogin() {
        return login;
    }
}
