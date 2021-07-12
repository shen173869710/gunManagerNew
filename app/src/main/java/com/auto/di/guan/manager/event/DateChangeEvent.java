package com.auto.di.guan.manager.event;

public class DateChangeEvent {
    private boolean group;
    private int postion;

    private int type;

    public DateChangeEvent(boolean group) {
        this.group = group;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DateChangeEvent(boolean group, int postion) {
        this.group = group;
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    /**
     *    是否要更新组信息
     */

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }
}
