package com.auto.di.guan.manager.event;

import com.auto.di.guan.manager.db.GroupInfo;

/**
 *  自动轮灌时间同步
 */
public class AutoTimeEvent {
    private GroupInfo groupInfo;

    private boolean isAout;
    public AutoTimeEvent(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public AutoTimeEvent(GroupInfo groupInfo, boolean isAout) {
        this.groupInfo = groupInfo;
        this.isAout = isAout;
    }

    public boolean isAout() {
        return isAout;
    }

    public void setAout(boolean aout) {
        isAout = aout;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }
}
