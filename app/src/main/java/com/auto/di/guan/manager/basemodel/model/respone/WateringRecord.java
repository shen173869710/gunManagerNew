package com.auto.di.guan.manager.basemodel.model.respone;

import java.io.Serializable;

public class WateringRecord implements Serializable {
    private long waterUserId;
    private long memberUserId;
    private String projectName;
    private String flowMeterCount;
    private long recordDate;
    private String fieldExt1;
    private String fieldExt2;
    private String fieldExt3;

    public long getWaterUserId() {
        return waterUserId;
    }

    public void setWaterUserId(long waterUserId) {
        this.waterUserId = waterUserId;
    }

    public long getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(long memberUserId) {
        this.memberUserId = memberUserId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFlowMeterCount() {
        return flowMeterCount;
    }

    public void setFlowMeterCount(String flowMeterCount) {
        this.flowMeterCount = flowMeterCount;
    }

    public long getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(long recordDate) {
        this.recordDate = recordDate;
    }

    public String getFieldExt1() {
        return fieldExt1;
    }

    public void setFieldExt1(String fieldExt1) {
        this.fieldExt1 = fieldExt1;
    }

    public String getFieldExt2() {
        return fieldExt2;
    }

    public void setFieldExt2(String fieldExt2) {
        this.fieldExt2 = fieldExt2;
    }

    public String getFieldExt3() {
        return fieldExt3;
    }

    public void setFieldExt3(String fieldExt3) {
        this.fieldExt3 = fieldExt3;
    }

    public String getFieldExt4() {
        return fieldExt4;
    }

    public void setFieldExt4(String fieldExt4) {
        this.fieldExt4 = fieldExt4;
    }

    private String fieldExt4;


}
