package com.wenbing.mvpdemo.beans;

/**
 * @author: wenbing
 * @date: 2020/3/16 15:40
 */
public class CoinBean {

    /**
     * coinCount : 451//总积分
     * rank : 7 //当前排名
     * userId : 2
     * username : x**oyang
     */

    private int coinCount;
    private int rank;
    private int userId;
    private String username;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
