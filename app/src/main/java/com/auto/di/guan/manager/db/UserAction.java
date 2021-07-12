package com.auto.di.guan.manager.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/7/16.
 */
public class UserAction {
    @Id(autoincrement = true)
    private Long id;

    private String userId;
    /**
     *   执行的命令
     *   开阀
     *   关阀
     */
    private String actionCmd;
    /***
     * 操作的类型
     *  0. 自动轮灌
     *  1. 手动单个操作
     *  2. 手动分组操作
     * **/
    private String actionName;
    /** 设备的id **/
    private int controlId;
    /** 保存的时间 **/
    private long actionTime;
    /** 动作的ID **/
    private int actionId;
    /**执行操作的类型**/
    private int actionType;
    /**执行操作的描述**/
    private String actionTypeName;
    /**执行操作的状态**/
    private int actionStatus;
    /**执行操作的状态显示的内容**/
    private String actionStatusName;
    private String userName;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getActionCmd() {
        return this.actionCmd;
    }
    public void setActionCmd(String actionCmd) {
        this.actionCmd = actionCmd;
    }
    public String getActionName() {
        return this.actionName;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public int getControlId() {
        return this.controlId;
    }
    public void setControlId(int controlId) {
        this.controlId = controlId;
    }
    public long getActionTime() {
        return this.actionTime;
    }
    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }
    public int getActionId() {
        return this.actionId;
    }
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }
    public int getActionType() {
        return this.actionType;
    }
    public void setActionType(int actionType) {
        this.actionType = actionType;
    }
    public String getActionTypeName() {
        return this.actionTypeName;
    }
    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }
    public boolean getActionStatus() {
        if (this.actionStatus == 1) {
            return true;
        }else {
            return false;
        }
    }
    public void setActionStatus(boolean actionStatus) {
        if (actionStatus) {
            this.actionStatus = 1;
        }else {
            this.actionStatus = 0;
        }
    }
    public String getActionStatusName() {
        return this.actionStatusName;
    }
    public void setActionStatusName(String actionStatusName) {
        this.actionStatusName = actionStatusName;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


}
