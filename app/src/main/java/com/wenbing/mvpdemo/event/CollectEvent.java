package com.wenbing.mvpdemo.event;


public class CollectEvent extends BaseEvent {
    boolean isCollected;
    int articleID;
    public CollectEvent(boolean isCollected,int articleID){
        this.isCollected = isCollected;
        this.articleID = articleID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
