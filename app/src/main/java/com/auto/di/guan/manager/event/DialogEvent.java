package com.auto.di.guan.manager.event;

public class DialogEvent {
    private boolean show;

    public DialogEvent(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
