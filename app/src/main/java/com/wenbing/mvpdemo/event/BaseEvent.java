package com.wenbing.mvpdemo.event;

import org.greenrobot.eventbus.EventBus;

public class BaseEvent {

    public void post(){
        EventBus.getDefault().post(this);
    }

    public void postSticky() {
        EventBus.getDefault().postSticky(this);
    }

    public void removeSticky() {
        EventBus.getDefault().removeStickyEvent(this);
    }

}