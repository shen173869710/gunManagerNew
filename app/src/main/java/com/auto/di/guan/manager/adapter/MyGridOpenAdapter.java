package com.auto.di.guan.manager.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;
import com.auto.di.guan.manager.dialog.MainoptionDialog;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.rtm.MessageSend;
import com.auto.di.guan.manager.utils.DensityUtil;
import com.auto.di.guan.manager.utils.GlideUtil;
import com.auto.di.guan.manager.utils.NoFastClickUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyGridOpenAdapter extends BaseQuickAdapter<DeviceInfo, BaseViewHolder> {

    public MyGridOpenAdapter(List<DeviceInfo> data) {
        super(R.layout.grid_item, data);
    }


    @Override
    protected void convert(BaseViewHolder holder, DeviceInfo deviceInfo) {
        /*****设备相关信息****/
        holder.setText(R.id.grid_item_device_id, deviceInfo.getDeviceSort()+"");

        TextView grid_item_device_name = holder.getView(R.id.grid_item_device_name);
        ImageView grid_item_device = holder.getView(R.id.grid_item_device);
        TextView grid_item_device_value = holder.getView(R.id.grid_item_device_value);
        TextView grid_item_device_id = holder.getView(R.id.grid_item_device_id);
        ImageView grid_item_device_value_icon = holder.findView(R.id.grid_item_device_value_icon);
        /*****第一个阀门****/
        RelativeLayout grid_item_left_layout = holder.getView(R.id.grid_item_left_layout);
        ImageView grid_item_left_group = holder.getView(R.id.grid_item_left_group);
        ImageView grid_item_left_image = holder.getView(R.id.grid_item_left_image);
        TextView grid_item_left_id = holder.getView(R.id.grid_item_left_id);
        TextView grid_item_left_sel = holder.getView(R.id.grid_item_left_sel);
        TextView grid_item_left_alias = holder.findView(R.id.grid_item_left_alias);

        /*****第二个阀门****/
        RelativeLayout grid_item_right_layout = holder.getView(R.id.grid_item_right_layout);
        ImageView grid_item_right_group = holder.getView(R.id.grid_item_right_group);
        ImageView grid_item_right_image = holder.getView(R.id.grid_item_right_image);
        TextView grid_item_right_id = holder.getView(R.id.grid_item_right_id);
        TextView grid_item_right_sel = holder.getView(R.id.grid_item_right_sel);
        TextView grid_item_right_alias = holder.findView(R.id.grid_item_right_alias);

        /******设备未绑定******/
        if (deviceInfo.getDeviceStatus() == Entiy.DEVEICE_UNBIND) {
            grid_item_device_name.setVisibility(View.INVISIBLE);
            grid_item_device.setVisibility(View.INVISIBLE);
            grid_item_device_value.setVisibility(View.INVISIBLE);
            grid_item_left_layout.setVisibility(View.INVISIBLE);
            grid_item_right_layout.setVisibility(View.INVISIBLE);
        }else {
            if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
                grid_item_device_name.setText(deviceInfo.getDeviceName()+"");
                grid_item_device_name.setVisibility(View.VISIBLE);
            }

            GlideUtil.loadDeviceImage(getContext(),grid_item_device, deviceInfo);
            grid_item_device_id.setVisibility(View.VISIBLE);
            grid_item_device.setVisibility(View.VISIBLE);
            grid_item_device_value.setVisibility(View.VISIBLE);
            grid_item_device_value.setText("电量"+deviceInfo.getElectricQuantity()+"%");
            grid_item_left_layout.setVisibility(View.VISIBLE);
            grid_item_left_sel.setVisibility(View.GONE);
            if (deviceInfo.getElectricQuantity() < 20) {
                grid_item_device_value_icon.setVisibility(View.VISIBLE);
            }

            ControlInfo controlInfo0 = deviceInfo.getValveDeviceSwitchList().get(0);
            if (controlInfo0.getValveGroupId() == 0) {
                grid_item_left_group.setVisibility(View.GONE);
            }else {
                grid_item_left_group.setVisibility(View.VISIBLE);
                GlideUtil.loadGroupImage(grid_item_right_group, controlInfo0.getValveGroupId());
            }

            if (controlInfo0.getValveStatus() == 0) {
                grid_item_left_image.setVisibility(View.INVISIBLE);
                grid_item_left_id.setText("");
                grid_item_left_alias.setText("");
            }else {
                grid_item_left_image.setVisibility(View.VISIBLE);
                GlideUtil.loadControlImage(getContext(),grid_item_left_image, controlInfo0);
                grid_item_left_id.setText(controlInfo0.getValveName()+"");
                grid_item_left_alias.setText(controlInfo0.getValveAlias()+"");
            }

            grid_item_right_layout.setVisibility(View.VISIBLE);
            grid_item_right_sel.setVisibility(View.GONE);

            ControlInfo controlInfo1 = deviceInfo.getValveDeviceSwitchList().get(1);
            if (controlInfo1.getValveGroupId() == 0) {
                grid_item_right_group.setVisibility(View.GONE);
            }else {
                grid_item_right_group.setVisibility(View.VISIBLE);
                GlideUtil.loadGroupImage(grid_item_right_group, controlInfo1.getValveGroupId());
            }
            if (controlInfo1.getValveStatus() == 0) {
                grid_item_right_image.setVisibility(View.INVISIBLE);
                grid_item_right_id.setText("");
                grid_item_right_alias.setText("");
            }else {
                grid_item_right_image.setVisibility(View.VISIBLE);
                GlideUtil.loadControlImage(getContext(),grid_item_right_image, controlInfo1);
                grid_item_right_id.setText(controlInfo1.getValveAlias()+"");
                grid_item_right_alias.setText(controlInfo1.getValveAlias()+"");
            }

            grid_item_left_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NoFastClickUtils.isFastClick()){
                        return;
                    }
                    openDevice(controlInfo0);
                }
            });

            grid_item_right_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NoFastClickUtils.isFastClick()){
                        return;
                    }
                    openDevice(controlInfo1);
                }
            });
        }
    }


    private void openDevice(final ControlInfo controlInfo) {
        String status = "关闭";
        if (controlInfo.getValveStatus() == Entiy.CONTROL_STATUS＿RUN) {
            status = "开启";
        }
        MainoptionDialog.ShowDialog((Activity) getContext(),controlInfo , "手动操作",status,new MainoptionDialog.ItemClick() {
            @Override
            public void onItemClick(int index) {
                if (index == 0) {
                    MessageSend.doSingleRead(controlInfo);
                }else if (index == 1) {
                    MessageSend.doSingleOpen(controlInfo);
                }else if (index == 2) {
                    MessageSend.doSingleClose(controlInfo);
                }
            }
        });
    }
}
