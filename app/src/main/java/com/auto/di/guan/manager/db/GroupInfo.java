package com.auto.di.guan.manager.db;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/9.
 */

public class GroupInfo implements Serializable {
    private Long id;
    private int groupId;
    private String groupName;
    /**轮灌的状态 **/
    private int groupStatus;
    private int groupImage;
    /** 轮灌优先级**/
    private int groupLevel;
    /** 运行的总时间**/
    private int groupTime;
    /** 已经运行的时间**/
    private int groupRunTime;
    // 是否参与轮灌设置
    private int groupIsJoin;
    /**轮灌是否已经暂停计时**/
    private int groupStop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        this.groupStatus = groupStatus;
    }

    public int getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(int groupImage) {
        this.groupImage = groupImage;
    }

    public int getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }

    public int getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(int groupTime) {
        this.groupTime = groupTime;
    }

    public int getGroupRunTime() {
        return groupRunTime;
    }

    public void setGroupRunTime(int groupRunTime) {
        this.groupRunTime = groupRunTime;
    }

    public boolean getGroupIsJoin() {
        if (groupIsJoin == 1) {
            return true;
        }else {
            return false;
        }
    }

    public void setGroupIsJoin(boolean groupIsJoin) {
        if (groupIsJoin) {
            this.groupIsJoin = 1;
        }else {
            this.groupIsJoin = 0;
        }
    }

    public boolean getGroupStop() {
        if (groupStop == 1) {
            return true;
        }else {
            return false;
        }
    }

    public void setGroupStop(boolean groupStop) {
        if (groupStop) {
            this.groupStop = 1;
        }else {
            this.groupStop = 0;
        }
    }
}
