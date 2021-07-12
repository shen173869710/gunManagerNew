package com.auto.di.guan.manager.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;

import java.util.List;

public class DiffDeviceCallback extends DiffUtil.ItemCallback<DeviceInfo>  {
    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areItemsTheSame(@NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
        return oldItem.getDeviceId()== newItem.getDeviceId();
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areContentsTheSame(@NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
        List<ControlInfo> oldList = oldItem.getValveDeviceSwitchList();
        List<ControlInfo> newList = newItem.getValveDeviceSwitchList();
        ControlInfo oldControl0 = oldList.get(0);
        ControlInfo oldControl1 = oldList.get(1);
        ControlInfo newControl0 = newList.get(0);
        ControlInfo newControl1 = newList.get(1);
        return  oldControl0.getValveStatus() == newControl0.getValveStatus()
                && oldControl0.getValveGroupId() == newControl0.getValveGroupId()
                && oldControl0.getValveAlias().equals(newControl0.getValveAlias())
                && oldControl1.getValveStatus() == newControl1.getValveStatus()
                && oldControl1.getValveGroupId() == newControl1.getValveGroupId()
                && oldControl1.getValveAlias().equals(newControl1.getValveAlias());
    }

    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     *
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    @Override
    public Object getChangePayload(@NonNull DeviceInfo oldItem, @NonNull DeviceInfo newItem) {
        return null;
    }
}
