package com.wenbing.mvpdemo.bean.base;

/**
 * @author gs_wenbing
 * @date 2019/9/3 15:57
 */
public class Request<T> {
    private T param;

    private String RequestTime;
    private String ClientIP;
    private String Sign;
    private String LogType;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public String getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(String requestTime) {
        RequestTime = requestTime;
    }

    public String getClientIP() {
        return ClientIP;
    }

    public void setClientIP(String clientIP) {
        ClientIP = clientIP;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public String getLogType() {
        return LogType;
    }

    public void setLogType(String logType) {
        LogType = logType;
    }
}
