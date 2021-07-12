package com.auto.di.guan.manager.event;

import com.auto.di.guan.manager.db.GroupInfo;

/**
 *  自动轮灌时间同步
 */
public class AutoTimeEvent {
    private GroupInfo groupInfo;
    public AutoTimeEvent(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }
}
