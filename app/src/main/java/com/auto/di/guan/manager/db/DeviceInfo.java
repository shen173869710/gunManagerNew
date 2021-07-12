package com.auto.di.guan.manager.db;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by czl on 2017/11/28.
 */
public class DeviceInfo implements Serializable{
    static final long serialVersionUID=3L;
    private Long id;
    // 设备id
    private int deviceId;
    // 设备名称
    private String deviceName;
    // 设备位置
    private int deviceSort;
    // 本地通信协议ID
    private String protocalId;
    //所有者ID
    private int userId;
    // 设备图片
    private String deviceImagePath;
    // 创建时间
    private String createTime;
    // 创建者
    private String createBy;
    // 设备电量
    private int electricQuantity;
    /**
     *   0  未添加
     *   1  已经添加
     */
    private int deviceStatus;
    private String remark;

    private ArrayList<ControlInfo>valveDeviceSwitchList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceSort() {
        return deviceSort;
    }

    public void setDeviceSort(int deviceSort) {
        this.deviceSort = deviceSort;
    }

    public String getProtocalId() {
        return protocalId;
    }

    public void setProtocalId(String protocalId) {
        this.protocalId = protocalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceImagePath() {
        return deviceImagePath;
    }

    public void setDeviceImagePath(String deviceImagePath) {
        this.deviceImagePath = deviceImagePath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(int electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<ControlInfo> getValveDeviceSwitchList() {
        return valveDeviceSwitchList;
    }

    public void setValveDeviceSwitchList(ArrayList<ControlInfo> valveDeviceSwitchList) {
        this.valveDeviceSwitchList = valveDeviceSwitchList;
    }
}
