package com.auto.di.guan.manager.db.sql;

import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

public class DeviceSql {

    public static List<ControlInfo> getAllControl() {
        List<ControlInfo> controlInfos = new ArrayList<>();
        int size = BaseApp.getDeviceInfos().size();
        for (int i = 0; i < size; i++) {
            DeviceInfo info = BaseApp.getDeviceInfos().get(i);
            controlInfos.addAll(info.getValveDeviceSwitchList());
        }
        return controlInfos;
    }

    public static ArrayList<DeviceInfo> getAllDevice() {
        return BaseApp.getDeviceInfos();
    }
}
